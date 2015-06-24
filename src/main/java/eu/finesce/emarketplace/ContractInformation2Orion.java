/*
 * (C) Copyright 2014 FINESCE-WP4.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 */
package eu.finesce.emarketplace;

import java.io.IOException;
import java.util.Properties;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import eu.finesce.emarketplace.context.ContractInformationContextElement;
import eu.finesce.emarketplace.domain.ContractInformation;
import eu.fiware.ngsi.official.ContextElement;
import eu.fiware.ngsi.official.ContextElementList;
import eu.fiware.ngsi.official.UpdateActionType;
import eu.fiware.ngsi.official.UpdateContextRequest;

/**
 * 
 * 
 */
@Path("/sendContractInformationData")
public class ContractInformation2Orion {

	/** The Constant logger. */
	private static final Log logger = LogFactory
			.getLog(ContractInformation2Orion.class);

	/** The register context path. */
	private static String REGISTER_CONTEXT_PATH = "";

	/** The orion server url. */
	private static String ORION_SERVER_URL = "";

	/** The prop. */
	Properties prop;

	/** The Constant REG_CTX_PATH. */
	private static final String REG_CTX_PATH = "contractinformation2orion.registerContexPath";

	/** The Constant ORION_SVR_URL. */
	private static final String ORION_SVR_URL = "contractinformation2orion.orionServerUrl";

	/** The Constant propertyPath. */
	private static final String propertyPath = "contractinformation2orion.properties";

	/** The event id. */
	private String EVENT_ID = "contractinformation2orion";

	/**
	 * Send contract information event ctx ev data to orion.
	 * 
	 * @param contractInformation
	 *            the contract information
	 * @return the response
	 */
	@POST
	@Consumes(MediaType.APPLICATION_XML)
	public final Response sendContractInformationEventCtxEvDataToOrion(
			ContractInformation contractInformation) {

		setConfigProperties();
		logger.info("Sending contract information data to Orion");
		contractInformation.setContractInformationID(EVENT_ID);
		contractInformation.setCurrentTime(System.currentTimeMillis() / 1000);

		Client client = ClientBuilder.newClient();
		WebTarget webTarget = client.target(ORION_SERVER_URL);
		WebTarget resourceWebTarget = webTarget.path(REGISTER_CONTEXT_PATH);

		UpdateContextRequest updContextRequest = new UpdateContextRequest();
		updContextRequest.setUpdateAction(UpdateActionType.APPEND);
		ContextElement element = new ContractInformationContextElement(
				contractInformation);
		ContextElementList elementList = new ContextElementList();
		elementList.getContextElements().add(element);
		updContextRequest.setContextElementList(elementList);
		Entity<UpdateContextRequest> sendXml = Entity.xml(updContextRequest);
		Response responseEntity = resourceWebTarget.request(
				MediaType.APPLICATION_XML).post(sendXml);
		return responseEntity;
	}

	/**
	 * Gets the contract information sample.
	 * 
	 * @return the contract information sample
	 */
	@GET
	@Produces(MediaType.APPLICATION_XML)
	public ContractInformation getContractInformationSample() {
		ContractInformation contractInformation = new ContractInformation();
		contractInformation.setContractInformationID("22222");
		contractInformation.setCostOfEnergyProducedByDERS(12.3);
		contractInformation.setCostOfSystemTransmissionPowerPlants(11.4);
		contractInformation.setCurrentTime(System.currentTimeMillis() / 1000);
		contractInformation.setEnergyCost(2.4);
		contractInformation
				.setValidityStartDate(System.currentTimeMillis() / 1000);
		contractInformation
				.setValidityEndDate((System.currentTimeMillis() + 1500009) / 1000);

		return contractInformation;
	}

	/**
	 * Sets the config properties.
	 */
	protected void setConfigProperties() {
		prop = new Properties();
		try {
			prop.load(this.getClass().getClassLoader()
					.getResourceAsStream(propertyPath));
			REGISTER_CONTEXT_PATH = prop.getProperty(REG_CTX_PATH);
			ORION_SERVER_URL = prop.getProperty(ORION_SVR_URL);
		} catch (IOException e) {
			logger.error("Error during getProperty ", e);
		}
	}

}
