package com.eniso.tama.dto;
// this class is to test the senmalAlert in MailTemplateController
public class Alert {

		String from ;
		String to;
		String progName; 
		String progLocation	;
		public String getFrom() {
			return from;
		}
		public void setFrom(String from) {
			this.from = from;
		}
		public String getTo() {
			return to;
		}
		public void setTo(String to) {
			this.to = to;
		}
		public String getProgName() {
			return progName;
		}
		public void setProgName(String progName) {
			this.progName = progName;
		}
		public String getProgLocation() {
			return progLocation;
		}
		public void setProgLocation(String progLocation) {
			this.progLocation = progLocation;
		}
		public Alert() {
			super();
		}
		
	
}
