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
package eu.finesce.emarketplace.context;

import eu.finesce.emarketplace.domain.ContractInformation;
import eu.fiware.ngsi.official.ContextAttribute;
import eu.fiware.ngsi.official.ContextAttributeList;
import eu.fiware.ngsi.official.ContextElement;
import eu.fiware.ngsi.official.EntityId;

/**
 * 
 */
public class ContractInformationContextElement extends ContextElement {

	/** The Constant CONTRACT_INFORMATION. */
	private static final String CONTRACT_INFORMATION = "Contractinformation";
	
	/** The Constant COST_OF_ENERGY_PRODUCED_BY_DERS. */
	private static final String COST_OF_ENERGY_PRODUCED_BY_DERS = "costOfEnergyProducedByDERS";
	
	/** The Constant COST_OF_SYSTEM_TRANSMISSION_POWERPLANTS. */
	private static final String COST_OF_SYSTEM_TRANSMISSION_POWERPLANTS = "costOfSystemTransmissionPowerPlants";
	
	/** The Constant ENERGYCOST. */
	private static final String ENERGYCOST = "energyCost";
	
	/** The Constant VALIDITY_START_DATE. */
	private static final String VALIDITY_START_DATE = "validityStartDate";
	
	/** The Constant VALIDITY_END_DATE. */
	private static final String VALIDITY_END_DATE = "validityEndDate";
	
	/** The Constant CURRENT_TIME. */
	private static final String CURRENT_TIME = "currentTime";
	

	/**
	 * Instantiates a new contract information context element.
	 *
	 * @param contractInformation the contract information
	 */
	public ContractInformationContextElement(ContractInformation contractInformation) {
		this.contextAttributeList = new ContextAttributeList();

		EntityId id = new EntityId();
		id.setId(contractInformation.getContractInformationID());
		id.setType(CONTRACT_INFORMATION);
		id.setIsPattern(false);
		this.setEntityId(id);
		ContextAttribute attribute = new ContextAttribute();

		// attributes
		attribute = new ContextAttribute();
		attribute.setName(CURRENT_TIME);
		attribute.setType("long");
		attribute.setContextValue(contractInformation.getCurrentTime());
		this.getContextAttributeList().getContextAttributes().add(attribute);
		
		attribute = new ContextAttribute();
		attribute.setName(COST_OF_ENERGY_PRODUCED_BY_DERS);
		attribute.setType("currency");
		attribute.setContextValue(contractInformation.getCostOfEnergyProducedByDERS());
		this.getContextAttributeList().getContextAttributes().add(attribute);

		attribute = new ContextAttribute();
		attribute.setName(COST_OF_SYSTEM_TRANSMISSION_POWERPLANTS);
		attribute.setType("currency");
		attribute.setContextValue(contractInformation.getCostOfSystemTransmissionPowerPlants());
		this.getContextAttributeList().getContextAttributes().add(attribute);

		attribute = new ContextAttribute();
		attribute.setName(ENERGYCOST);
		attribute.setType("currency");
		attribute.setContextValue(contractInformation.getEnergyCost());
		this.getContextAttributeList().getContextAttributes().add(attribute);

		attribute = new ContextAttribute();
		attribute.setName(VALIDITY_START_DATE);
		attribute.setType("long");
		attribute.setContextValue(contractInformation.getValidityStartDate());
		this.getContextAttributeList().getContextAttributes().add(attribute);
		
		attribute = new ContextAttribute();
		attribute.setName(VALIDITY_END_DATE);
		attribute.setType("long");
		attribute.setContextValue(contractInformation.getValidityEndDate());
		this.getContextAttributeList().getContextAttributes().add(attribute);
	}

}
