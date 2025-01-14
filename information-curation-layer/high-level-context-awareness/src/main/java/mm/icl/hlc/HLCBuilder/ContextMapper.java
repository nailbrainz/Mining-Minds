/**
* 
* Copyright [2016] [Claudia Villalonga & Muhammad Asif Razzaq]
* Licensed under the Apache License, Version 2.0 (the "License");
* you may not use this file except in compliance with the License.
* You may obtain a copy of the License at http://www.apache.org/licenses/LICENSE-2.0
* Unless required by applicable law or agreed to in writing, software distributed under 
* the License is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF
*  ANY KIND, either express or implied.
*  See the License for the specific language governing permissions and limitations under the License.
*/
package mm.icl.hlc.HLCBuilder;
import com.hp.hpl.jena.datatypes.xsd.XSDDatatype;
import com.hp.hpl.jena.ontology.Individual;
import com.hp.hpl.jena.ontology.OntClass;
import com.hp.hpl.jena.ontology.OntModel;
import com.hp.hpl.jena.ontology.OntModelSpec;
import com.hp.hpl.jena.rdf.model.Literal;
import com.hp.hpl.jena.rdf.model.ModelFactory;
import com.hp.hpl.jena.sparql.util.Utils;

import java.util.Calendar;
import java.util.Random;

import org.apache.log4j.Logger;

import mm.hlca.TestHLCAMapper;
import mm.icl.hlc.ContextOntologyManager.ContextHandler;
import mm.icl.hlc.OntologyTools.ContextOntology;
import mm.icl.hlc.OntologyTools.HLCA;
import mm.icl.hlc.OntologyTools.LowLevelContext;
import mm.icl.utils.FileUtil;

/**
 * Context Mapper. Component of HLCA which maps into ontological format the
 * labels plus metadata generated by the LLC Recognizers.
 * 
 * @author Claudia Villalonga and Muhammad Asif Razzaq
 * @version 2.5
 * @since 2015-11-12
 */
public class ContextMapper {
	
	/** The Constant logger. */
	final static Logger logger = Logger.getLogger(ContextMapper.class);
	/**
	 * Context Ontology which represents the Mining Minds Context Model.
	 */
	private ContextOntology ont;
	/**
	 * Identifier of the Context Mapper.
	 */
	private int mapperId;
	/**
	 * Counter of the number LLC instances generated by the Context Mapper.
	 */
	private long llcInstNum;
	
	/**
	 * Constructor of a new Context Mapper.
	 *
	 */
	
	private ContextHandler contextHandler = ContextHandler.getContextHandler();  
	
	/**
	 * Instantiates a new context mapper.
	 *
	 * @param ont the ont
	 */
	public ContextMapper(ContextOntology ont) {
		this.ont = ont;
		Random rand = new Random();
		this.mapperId = rand.nextInt(Integer.MAX_VALUE);
		this.llcInstNum = 0;
	}
	/**
	 * Method to map into ontological format the labels plus metadata generated
	 * by the LLC Recognizers.
	 * 
	 * @param llcLabel
	 *            Label (or category) of the new LLC.
	 * @param userId
	 *            Identifier of the user which has associated the LLC.
	 * @param startTime
	 *            Start time of the new LLC in the XSD dateTime format
	 *            "2015-08-10T11:05:30+09:00".
	 */
	public void mapLLC(String llcLabel, String userId, Calendar startTime) {
		OntModel llcModel = ModelFactory.createOntologyModel(OntModelSpec.OWL_DL_MEM);
		llcModel.setNsPrefix(HLCA.nsPrefix, HLCA.ns);
		Individual user = llcModel.createIndividual(HLCA.ns + "user_" + userId, ont.getUserClass());
		Literal time = llcModel.createTypedLiteral(Utils.calendarToXSDDateTimeString(startTime), XSDDatatype.XSDdateTime);
		if (llcLabel.equals(HLCA.noActivity))
			contextHandler.finalizePreviousLlc(ont.getLlcCategoryClass(HLCA.activityClassName), user, time); 
		else if (llcLabel.equals(HLCA.noLocation)){
			logger.error("No location recieved by Context Mapper");
			contextHandler.finalizePreviousLlc(ont.getLlcCategoryClass(HLCA.locationClassName), user, time); 
		}
		else if (llcLabel.equals(HLCA.noEmotion))
			contextHandler.finalizePreviousLlc(ont.getLlcCategoryClass(HLCA.emotionClassName), user, time);  
		else if (llcLabel.equals(HLCA.noFood)) 
		{	contextHandler.finalizePreviousLlc(ont.getLlcCategoryClass(HLCA.foodClassName), user, time);     
		}
		else {
			OntClass llcClass = ont.getLlcTypeClass(HLCA.ns + llcLabel);
			if (llcClass != null) {
				long instanceId = 0;
				synchronized(this){
					instanceId = this.llcInstNum;
					this.llcInstNum++;
				}
				Individual llcIndiv = llcModel.createIndividual(HLCA.ns + "llc_" + String.format("%010d", this.mapperId)
						+ "_" + String.format("%019d", instanceId), llcClass);
				llcIndiv.addProperty(ont.getContextOfProp(), user);
				llcIndiv.addProperty(ont.getStartTimeProp(), time);
				LowLevelContext llc = new LowLevelContext(llcModel, ont);
				contextHandler.storeNewLlc(llc); 
			}
			else{
				logger.error ("[Context Mapper] LLC could not be mapped. " + llcLabel + " is not a valid label.");
				logger.info("[Context Mapper] LLC could not be mapped. " + llcLabel + " is not a valid label."); 
			}
		}
	}
	
	/**
	 * Gets the context handler.
	 *
	 * @return the context handler
	 */
	public ContextHandler getContextHandler() {
		return contextHandler;
	}
	
	/**
	 * Sets the context handler.
	 *
	 * @param contextHandler the new context handler
	 */
	public void setContextHandler(ContextHandler contextHandler) {
		this.contextHandler = contextHandler;
	}

}