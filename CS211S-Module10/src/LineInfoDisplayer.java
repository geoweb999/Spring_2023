import javafx.scene.shape.Line;
@FunctionalInterface
public interface LineInfoDisplayer {

    String getInfo(Line line);

    public static enum InfoType {
        DISTANCE, MIDPOINT, VERT_HORZ, SLOPE;
    }
      
    public static LineInfoDisplayer createLineInfoDisplayer(InfoType type) {

    	switch (type) {
    		case DISTANCE:
    			return (Line line) -> {
    				if (line == null) {
    		    		return null;
    		    	}
    		    	double x1 = line.getStartX();
    		        double y1 = line.getStartY();
    		        double x2 = line.getEndX();
    		        double y2 = line.getEndY();
    		        
    		        double deltaX = x2 - x1;
    		        double deltaY = y2 - y1;
    		        
    		        double distance = Math.sqrt(deltaX * deltaX + deltaY * deltaY);
    		        return String.format("Distance: %.2f", distance);
    			};
    		case MIDPOINT:
    			return (Line line) -> {
			        if (line == null) {
			            return null;
			        }
			        double x1 = line.getStartX();
		            double y1 = line.getStartY();
		            double x2 = line.getEndX();
		            double y2 = line.getEndY();
		            
		            double midpointX = (x1 + x2) / 2.0;
		            double midpointY = (y1 + y2) / 2.0;
		            
		            return "Midpoint: (" + String.format("%.0f", midpointX) + ", " + String.format("%.0f", midpointY) + ")";
    			};
    		case VERT_HORZ:
    			return (Line line) -> {
    		        if (line == null) {
    		            return null;
    		        }
    		        double x1 = line.getStartX();
    		        double y1 = line.getStartY();
    		        double x2 = line.getEndX();
    		        double y2 = line.getEndY();

    		        if (x1 == x2) {
    		            return "Vertical";
    		        } else if (y1 == y2) {
    		            return "Horizontal";
    		        } else {
    		            return "Not vertical or horizontal";
    		        }
    		    };
    		case SLOPE:
    			return (Line line) -> {
    		        if (line == null) {
    		            return null;
    		        }
    		        double x1 = line.getStartX();
    		        double y1 = line.getStartY();
    		        double x2 = line.getEndX();
    		        double y2 = line.getEndY();
    		        
    		        if (x2 - x1 == 0) {
    		        	return "Slope: Infinity";
    		        } else {
    		        	double slope = (y2 - y1) / (x2 - x1);
    		        	return String.format("Slope: %.2f", slope);
    		        }
    			};
    		default:
    			return null;
    	}
    }

}
