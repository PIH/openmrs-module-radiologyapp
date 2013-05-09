/*
 * The contents of this file are subject to the OpenMRS Public License
 * Version 1.0 (the "License"); you may not use this file except in
 * compliance with the License. You may obtain a copy of the License at
 * http://license.openmrs.org
 *
 * Software distributed under the License is distributed on an "AS IS"
 * basis, WITHOUT WARRANTY OF ANY KIND, either express or implied. See the
 * License for the specific language governing rights and limitations
 * under the License.
 *
 * Copyright (C) OpenMRS, LLC.  All Rights Reserved.
 */

package org.openmrs.module.radiologyapp;

import org.openmrs.Encounter;
import org.openmrs.Patient;
import org.openmrs.api.OpenmrsService;
import org.openmrs.module.emr.EmrContext;

import java.util.List;

public interface RadiologyService extends OpenmrsService {

    Encounter placeRadiologyRequisition(EmrContext emrContext, RadiologyRequisition requisition);

    /**
     * Converts a radiology report to an encounter and saves it
     * (Note that this method cannot be used to
     *
     * @param radiologyReport
     * @return
     */
    Encounter saveRadiologyReport(RadiologyReport radiologyReport);

    /**
     * Converts a radiology study to an encounter and saves it
     *
     * @param radiologyStudy
     * @return
     */
    Encounter saveRadiologyStudy(RadiologyStudy radiologyStudy);

    /**
     * Fetches the radiology order with the specified accession number
     *
     * @param accessionNumber
     * @return
     */
    RadiologyOrder getRadiologyOrderByAccessionNumber(String accessionNumber);

    /**
     * Fetches the radiology study with the specified accession number
     * (If there is no explicit radiology study encounter with this accession number,
     * it tries to derive this information from any radiology reports with the same accession number)
     *
     * @param accessionNumber
     * @return
     */
    RadiologyStudy getRadiologyStudyByAccessionNumber(String accessionNumber);

    /**
     * Fetches all radiology reports with the specified accession number
     * (i.e., all the reports for a single study)
     *
     * @param accessionNumber
     * @return
     */
    List<RadiologyReport> getRadiologyReportsByAccessionNumber(String accessionNumber);

    /**
     * Returns all the radiology studies for the selected patient, sorted by date, with most recent first
     * This method determines fetches studies by 1) fetching all Radiology Study encounters, and then
     * 2) fetching all orphaned Radiology Reports (i.e., Radiology Report encounters where there is no
     * Radiology Study encounter with the same accession number) and creating transient Radiology Study
     * objects for these report sets
     *
     * @param patient
     * @return
     */
    List<RadiologyStudy> getRadiologyStudiesForPatient(Patient patient);

}