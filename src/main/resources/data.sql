select encounter_type_id, name, description, uuid
from encounter_type into OUTFILE '/home/isanteplus/person_attribute_type.csv' FIELDS TERMINATED BY ',' ENCLOSED BY '"' LINES TERMINATED BY '\n';

mysql
-u root -pwahbahphoeth -D openmrs -e 'select encounter_type_id, name, description, uuid from encounter_type'  >> encounter_type.csv

select c.concept_id, cn.name, c.uuid
from concept c,
     concept_class cc,
     concept_name cn
where c.class_id = cc.concept_class_id
  and cn.concept_id = c.concept_id
  and cc.concept_class_id = 3
  and cn.locale = 'fr';


mysql
-u root -pwahbahphoeth -D openmrs -e 'select c.concept_id, cn.name, c.uuid from concept c, concept_class cc, concept_name cn where c.class_id = cc.concept_class_id and cc.concept_class_id = 3 and cn.concept_id = c.concept_id and cn.locale = "fr"' >> drug_concept.csv


