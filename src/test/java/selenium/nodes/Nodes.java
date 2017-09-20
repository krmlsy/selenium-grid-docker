package selenium.nodes;

public enum Nodes {
	
	
	FIREFOX,
	CHROME,
	INTERNET_EXPLORER;
	
    public static enum Chrome implements NodeInterface {

        VERSION_60("60.0.3112.113"),
        VERSION_61("61.0.3163.79"),
        BROWSER_NAME("chrome"),
        PLATFORM("LINUX");
    	
        private final String value;

        private Chrome(final String value) {
            this.value = value;
        }

        public String getValue() {
            return value;
        }
    }
	
    public static enum Firefox implements NodeInterface {

        VERSION_52("52.0.2"),
        VERSION_55("55.0.3"),
        BROWSER_NAME("firefox"),
        PLATFORM("LINUX");
    	
        private final String value;

        private Firefox(final String value) {
            this.value = value;
        }

        public String getValue() {
            return value;
        }
    }
    
    public static enum InternetExplorer implements NodeInterface {

        VERSION_11("11.0.9600.17107"),
        BROWSER_NAME("internet explorer"),
        PLATFORM("WINDOWS");
    	
    	
        private final String value;

        private InternetExplorer(final String value) {
            this.value = value;
        }

        public String getValue() {
            return value;
        }
        
    }

}
