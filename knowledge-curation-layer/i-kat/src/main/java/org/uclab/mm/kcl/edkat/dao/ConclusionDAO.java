/*
 Copyright [2016] [Taqdir Ali]

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at http://www.apache.org/licenses/LICENSE-2.0
Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.

 */
package org.uclab.mm.kcl.edkat.dao;

import java.util.List;

import org.uclab.mm.kcl.edkat.datamodel.Conclusion;





/**
* This is Data Access Object interface for the conclusion this DAO provides interfaces to 
* create, update, retrieve of conclusions of a specific rule
 * @author  Taqdir Ali
 * @version 1.0
 * @since   2015-08-16
 * */

public interface ConclusionDAO {

	
	/**
	 * This interface function is for add new Conclusion
	 * @param objConclusion
	 * @return object of Conclusion
	*/
	public Conclusion addConclusion(Conclusion objConclusion);
	
	/**
	 *  This interface function is for update the existing Conclusion
	 *  @param objConclusion
	 *  @return object of Conclusion
	*/  
	public Conclusion updateConclusion(Conclusion objConclusion);
	
	/**
	 * This function get all conclusions of the rules
	 * @return List of Conclusion
	*/
	public List<Conclusion> listConclusion();
	
	/**
	 * This function get a specific conclusion by conclusion id (primary key)
	 * @param id
	 * @return Object of Conclusion
	*/
	public Conclusion getConclusionById(int id);
	
	/**
	 * This function delete a specific conclusion by conclusion id (primary key)
	 * @param id
	 * 
	*/
    public void removeConclusion(int id);
    
}
