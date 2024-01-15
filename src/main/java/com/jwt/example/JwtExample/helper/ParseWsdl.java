package com.jwt.example.JwtExample.helper;

import org.apache.cxf.BusException;
import org.apache.cxf.wsdl.WSDLManager;
import org.apache.cxf.wsdl11.WSDLManagerImpl;
import org.springframework.stereotype.Component;

import javax.wsdl.Definition;
import javax.wsdl.PortType;
import javax.xml.namespace.QName;
import java.io.File;
import java.util.HashMap;
import java.util.Map;
import javax.wsdl.Input;
import javax.wsdl.Operation;
import javax.wsdl.Part;

@Component
public class ParseWsdl {

        public Map<String,QName> getWsdl() throws BusException, javax.wsdl.WSDLException {
            String wsdlLocation =FileUploadHelper.setFile();

            // Create a WSDLManager
            WSDLManager wsdlManager = new WSDLManagerImpl();
            System.out.println(wsdlLocation);

            // Parse the WSDL file
            Definition wsdlDefinition = wsdlManager.getDefinition(wsdlLocation);

            wsdlManager.getDefinition(wsdlLocation);
            Map<String, QName> operationMap = getOperationNames(wsdlDefinition);

            // Display the map of method names
            for (Map.Entry<String, QName> entry : operationMap.entrySet()) {
                System.out.println("Method: " + entry.getKey() + ", QName: " + entry.getValue());
            }
            return operationMap;
//        System.out.println(wsdlManager.getDefinitions());
//            System.out.println("WSDL file parsed successfully!");
        }

        private static Map<String, QName> getOperationNames(Definition wsdlDefinition) {
            Map<String, QName> operationMap = new HashMap<>();

            if (wsdlDefinition != null) {
                Map<QName, PortType> portTypes = wsdlDefinition.getAllPortTypes();
                for (PortType portType : portTypes.values()) {
                    for (Operation operation : (Iterable<Operation>) portType.getOperations()) {
                        // Get the input message
                        Input input = operation.getInput();
                        if (input != null) {
                            // Get the parts of the input message
                            Map<String, Part> parts = input.getMessage().getParts();
                            for (Part part : parts.values()) {
                                // Add the operation name and corresponding QName to the map
                                operationMap.put(operation.getName(), part.getElementName());
                            }
                        }
                    }
                }
            }

            return operationMap;
        }








}
