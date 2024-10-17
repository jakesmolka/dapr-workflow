package org.example.flows;

import ca.uhn.fhir.context.FhirContext;
import ca.uhn.fhir.rest.client.api.IGenericClient;
import io.dapr.workflows.runtime.WorkflowActivity;
import io.dapr.workflows.runtime.WorkflowActivityContext;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.flows.getdemographicresource.GetDemographicResourceInput;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Slf4j
@AllArgsConstructor
@Component
public class GetResourceActivity implements WorkflowActivity {

    private final IGenericClient client;
    private final FhirContext fhirContext;

    @Override
    public Object run(WorkflowActivityContext ctx) {
        log.info("Starting Activity: {}", ctx.getName());

        GetDemographicResourceInput getDemographicResourceInput = ctx.getInput(GetDemographicResourceInput.class);
        var result = client.read().resource(getDemographicResourceInput.type()).withId(getDemographicResourceInput.id()).execute();
        String json = fhirContext.newJsonParser().encodeResourceToString(result);

        log.info("Message Received from input: {}/{}", getDemographicResourceInput.type(), getDemographicResourceInput.id());
        log.info("Sending message to output: {}", json);

        return json;
    }
}
