package com.example.messagingstompwebsocket;

import java.util.*;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@RestController
public class XfdfDataApiController {
	@RequestMapping(value = "/annotation-handler/{documentId}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public List<XfdfMessage> index(@PathVariable(value="documentId") String documentId) {
        // Generate your XFDF response based on the data you stored in your database here. These are sample annotations
        return Arrays.asList(
            new XfdfMessage(documentId, "NEW_ANNOT_ID", "<?xml version=\"1.0\" encoding=\"UTF-8\" ?><xfdf xmlns=\"http://ns.adobe.com/xfdf/\" xml:space=\"preserve\"><fields /><add><text page=\"0\" rect=\"439.74,588.67,459.74,608.67\" color=\"#FFCD45\" flags=\"print,nozoom,norotate\" name=\"f4e555fc-6e00-b44b-1de7-1a1eee215188\" title=\"Michael's Comment Annot\" subject=\"Note\" date=\"D:20200828164617-04'00'\" creationdate=\"D:20200828164617-04'00'\" icon=\"Comment\" statemodel=\"Review\"/></add><modify /><delete /></xfdf>"),
            new XfdfMessage(documentId, "NEW_ANNOT_ID", "<?xml version=\"1.0\" encoding=\"UTF-8\" ?><xfdf xmlns=\"http://ns.adobe.com/xfdf/\" xml:space=\"preserve\"><fields /><add><ink xmlns=\"http://ns.adobe.com/xfdf/\" page=\"0\" rect=\"27.79850518608908,337.13850518608905,295.24149481391095,528.5714948139109\" color=\"#E44234\" flags=\"print\" name=\"1d270620-c9e4-b7ab-7d4f-ba2067b8b13d\" title=\"Michael's pen annotation\" subject=\"Free Hand\" date=\"D:20200906174727-07'00'\" width=\"10.201494813910921\" creationdate=\"D:20200906174717-07'00'\"><inklist><gesture>47.51,518.37;49.41,518.37;49.41,516.47;49.41,512.6700000000001;49.41,505.06;49.41,495.56;49.41,486.06;49.41,476.56;49.41,467.06;47.51,448.06;45.61,436.66;41.81,419.55;41.81,408.15;39.9,396.75;38,391.05;38,387.25;38,385.35</gesture><gesture>51.31,436.66;51.31,436.66;57.01,436.66;64.61,436.66;72.21,436.66;79.81,436.66;85.51,436.66;89.31,436.66;91.21,436.66;91.21,436.66;91.21,436.66</gesture><gesture>104.51,503.16;104.51,503.16;104.51,499.36;104.51,493.66;104.51,484.16;104.51,478.46;104.51,472.76;104.51,467.06;104.51,461.36;104.51,459.46;104.51,455.66;104.51,451.86;104.51,446.16;104.51,440.46;104.51,434.76;104.51,427.15;104.51,423.35;104.51,421.45;104.51,421.45;104.51,419.55;104.51,419.55;104.51,417.65;104.51,417.65;104.51,415.75;104.51,413.85;102.61,410.05;102.61,408.15;100.71,404.35;100.71,402.45;100.71,400.55;98.81,396.75;98.81,394.85;96.91,391.05;96.91,389.15;96.91,389.15</gesture><gesture>125.42,406.25;127.32,406.25;131.12,408.15;133.02,408.15;138.72,410.05;144.42,413.85;148.22,415.75;152.02,415.75;152.02,415.75;152.02,417.65;152.02,417.65;150.12,417.65;146.32,417.65;142.52,417.65;136.82,419.55;133.02,419.55;131.12,419.55;129.22,419.55;125.42,415.75;125.42,413.85;121.62,406.25;117.81,402.45;115.91,400.55;114.01,396.75;112.11,396.75;112.11,394.85;112.11,392.95;112.11,392.95;112.11,391.05;115.91,391.05;117.81,391.05;121.62,391.05;125.42,391.05;127.32,389.15;129.22,387.25;131.12,387.25;133.02,387.25;133.02,387.25;134.92,387.25;136.82,387.25;138.72,389.15;144.42,392.95;146.32,392.95;153.92,394.85;157.72,394.85</gesture><gesture>188.12,487.96;188.12,487.96;188.12,486.06;188.12,482.26;188.12,476.56;188.12,470.86;188.12,465.16;188.12,459.46;188.12,455.66;188.12,449.96;188.12,446.16;188.12,436.66;188.12,430.95;188.12,423.35;188.12,417.65;188.12,415.75;188.12,411.95;188.12,411.95;188.12,410.05;188.12,410.05;188.12,408.15;188.12,408.15</gesture><gesture>188.12,406.25;188.12,404.35;188.12,402.45;188.12,400.55;188.12,398.65;188.12,396.75</gesture><gesture>218.53,482.26;218.53,482.26;218.53,480.36;218.53,476.56;218.53,474.66;218.53,470.86;218.53,468.96;218.53,465.16;218.53,457.56;218.53,444.26;218.53,436.66;218.53,404.35;218.53,381.55;218.53,370.15;218.53,368.25;218.53,353.05;218.53,351.14;218.53,349.24;218.53,347.34;218.53,349.24;218.53,351.14;220.43,353.05</gesture><gesture>252.73,398.65;252.73,398.65;250.83,398.65;247.03,396.75;245.13,394.85;243.23,394.85;243.23,392.95;243.23,391.05;243.23,389.15;243.23,387.25;243.23,383.45;243.23,381.55;243.23,379.65;243.23,377.75;243.23,373.95;243.23,372.05;243.23,370.15;243.23,370.15;243.23,368.25;247.03,368.25;250.83,368.25;256.53,368.25;264.13,368.25;271.73,368.25;277.43,368.25;279.33,368.25;281.24,370.15;281.24,372.05;283.14,373.95;285.04,377.75;285.04,381.55;285.04,389.15;283.14,392.95;279.33,396.75;273.63,400.55;267.93,402.45;262.23,406.25;256.53,406.25;252.73,406.25;247.03,406.25;243.23,408.15;241.33,408.15;239.43,408.15</gesture></inklist></ink></add><modify /><delete /></xfdf>")
         );
	}    
}