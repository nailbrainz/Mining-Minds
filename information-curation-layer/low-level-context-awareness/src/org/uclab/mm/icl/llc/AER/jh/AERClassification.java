/*
Copyright [2016] [Jae Hun, Bang]

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

   http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
*/
package org.uclab.mm.icl.llc.AER.jh;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

import org.uclab.mm.icl.llc.LLCManager.ContextLabel;
import org.uclab.mm.icl.llc.MachineLearningTools.ExtClassification;
import org.uclab.mm.icl.llc.MachineLearningTools.classification.Classification;

import weka.classifiers.Classifier;
import weka.classifiers.Evaluation;
import weka.core.Attribute;
import weka.core.FastVector;
import weka.core.Instance;
import weka.core.Instances;
/**
 * This Class build the MMv2.5 Audio based ER predictive model by all of classifier from weka library
 * @author Bang Gae
 *
 */
public class AERClassification extends Classification<double[]> {
	ExtClassification ec;

	
	@Override
	public String Classify(double[] data) {
		// TODO Auto-generated method stub
		//result = getEmotion(learningDataset, predictiveModel, data);
		return "NoEmotion";
	}
	/**
	 * classify Emotion
	 * @param data
	 * @return int
	 */
	public int classifyEmotion(double[] data) {
		int result = 0;
		try {
			result = ec.classify(data);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}
	
	/**
	 * set training data path, features information, labels and classifier
	 * @param filename
	 * @param FEATURES_NUM
	 * @param label
	 * @param classifier
	 */
	public AERClassification(String filename, int FEATURES_NUM, String[] labels, Classifier classifier ) {
		ec = new ExtClassification(filename, FEATURES_NUM, labels, classifier);
	}
	

}
