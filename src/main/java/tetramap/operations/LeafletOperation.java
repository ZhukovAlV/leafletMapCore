package tetramap.operations;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import tetramap.js.Identifiable;

import java.io.Serializable;

public class LeafletOperation implements Serializable {

    private static final long serialVersionUID = -7752937288867492721L;

    private String layerId;
 //   private boolean controlOperation;
    private String functionName;
    private Serializable[] arguments;
    private final ObjectMapper mapper = new ObjectMapper();

    public LeafletOperation(Identifiable target, String functionName, Serializable... arguments) {
        this.layerId = target.getUuid();
        this.functionName = functionName;
        this.arguments = arguments;
       // this.controlOperation = target instanceof LeafletControl;
    }

    /**
     * @return the layerId
     */
    public String getLayerId() {
        return layerId;
    }

    /**
     * @return the functionName
     */
    public String getFunctionName() {
        return functionName;
    }
    
    
/*    public boolean isControlOperation() {
        return controlOperation;
    }*/

    /**
     * @return the arguments
     */
    public String getArguments() {
        try {
            return mapper.writeValueAsString(arguments);
        } catch (JsonProcessingException e) {
            throw new RuntimeException("Unable to convert arguments to JSON.", e);
        }
    }
}
