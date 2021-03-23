CREATE SEQUENCE usage_analysis_seq;



CREATE TABLE usage_analysis
( method VARCHAR(200) NOT NULL , operation VARCHAR(200) NOT NULL , url VARCHAR(500) NOT NULL ,
  ip VARCHAR(200) NOT NULL ,id INT NOT NULL DEFAULT NEXTVAL ('usage_analysis_seq') , PRIMARY KEY (id));