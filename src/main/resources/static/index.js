const viewerElement = document.getElementById('viewer');

const socket = new SockJS('/gs-guide-websocket');
stompClient = Stomp.over(socket);
stompClient.connect({}, (frame) => {
    console.log('Connected: ' + frame);
});

let annotManager = null;
const DOCUMENT_ID = 'webviewer-demo-1';
const SESSION_ID = '6471ebe2-0e85-4bb9-a288-692a54a49454';
const hostName = window.location.hostname;
const nameList = ['Andy', 'Andrew', 'Logan', 'Justin', 'Matt', 'Sardor', 'Zhijie', 'James', 'Kristian', 'Mary', 'Patricia', 'Jennifer', 'Linda', 'David', 'Joseph', 'Thomas', 'Naman', 'Nancy', 'Sandra'];
const serializer = new XMLSerializer();

WebViewer({
    path: 'lib', // path to the PDFTron 'lib' folder
    initialDoc: 'https://pdftron.s3.amazonaws.com/downloads/pl/webviewer-demo.pdf',
}, viewerElement).then(instance => {

    // Instance is ready here
    instance.openElements(['leftPanel']);
    annotManager = instance.docViewer.getAnnotationManager();
    // Assign a random name to client
    annotManager.setCurrentUser(nameList[Math.floor(Math.random() * nameList.length)]);
    annotManager.on('annotationChanged', async e => {
        // If annotation change is from import, return
        if (e.imported) {
            return;
        }

        const xfdfString = await annotManager.exportAnnotCommand();
        // Parse xfdfString to separate multiple annotation changes to individual annotation change
        const parser = new DOMParser();
        const commandData = parser.parseFromString(xfdfString, 'text/xml');
        const addedAnnots = commandData.getElementsByTagName('add')[0];
        const modifiedAnnots = commandData.getElementsByTagName('modify')[0];
        const deletedAnnots = commandData.getElementsByTagName('delete')[0];

        // List of added annotations
        addedAnnots.childNodes.forEach((child) => {
            sendAnnotationChange(child, 'add');
        });

        // List of modified annotations
        modifiedAnnots.childNodes.forEach((child) => {
            sendAnnotationChange(child, 'modify');
        });

        // List of deleted annotations
        deletedAnnots.childNodes.forEach((child) => {
            sendAnnotationChange(child, 'delete');
        });
    });

    stompClient.subscribe(`/receive/receive-annotation-updates/${SESSION_ID}`, async (xfdfMessage) => {
        console.log(xfdfMessage.body);
        const annotation = JSON.parse(xfdfMessage.body).xfdfString;
        const annotations = await annotManager.importAnnotCommand(annotation);
        await annotManager.drawAnnotationsFromList(annotations);
    });
});

viewerElement.addEventListener('documentLoaded', () => {
    loadXfdfStrings(DOCUMENT_ID).then((rows) => {
        JSON.parse(rows).forEach(async row => {
            const annotations = await annotManager.importAnnotCommand(row.xfdfString);
            await annotManager.drawAnnotationsFromList(annotations);
        });
    });
});

const loadXfdfStrings = (documentId) => {
    return new Promise((resolve, reject) => {
        fetch(`/annotation-handler/${documentId}`, {
            method: 'GET',
        }).then((res) => {
            if (res.status < 400) {
                res.text().then(xfdfStrings => {
                    resolve(xfdfStrings);
                });
            } else {
                reject(res);
            }
        });
    });
};


// wrapper function to convert xfdf fragments to full xfdf strings
const convertToXfdf = (changedAnnotation, action) => {
    let xfdfString = `<?xml version="1.0" encoding="UTF-8" ?><xfdf xmlns="http://ns.adobe.com/xfdf/" xml:space="preserve"><fields />`;
    if (action === 'add') {
        xfdfString += `<add>${changedAnnotation}</add><modify /><delete />`;
    } else if (action === 'modify') {
        xfdfString += `<add /><modify>${changedAnnotation}</modify><delete />`;
    } else if (action === 'delete') {
        xfdfString += `<add /><modify /><delete>${changedAnnotation}</delete>`;
    }
    xfdfString += `</xfdf>`;
    return xfdfString;
}

// helper function to send annotation changes to WebSocket server
const sendAnnotationChange = (annotation, action) => {
    if (annotation.nodeType !== annotation.TEXT_NODE) {
        const annotationString = serializer.serializeToString(annotation);
        stompClient.send(`/send/send-annotation-updates/${SESSION_ID}`, {}, JSON.stringify({
            documentId: DOCUMENT_ID,
            annotationId: annotation.getAttribute('name'),
            xfdfString: convertToXfdf(annotationString, action)
        }));
    }
}
