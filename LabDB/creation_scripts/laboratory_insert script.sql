-- insert sqript
use laboratory;

-- roles
insert into roles(role_name)
values  ('Καθηγητής'),('Αναπληρωτής Καθηγητής'),('Επίκουρος Καθηγητής'),('Λέκτορας'),('Ερευνητής'),
('Υποψήφιος Διδάκτορας'),('Μεταπτυχιακός Φοιτητής'),('Προπτυχιακός Φοιτητής');

select * from roles order by role_id asc;

show tables;


-- courses
select * from courses;

insert into courses(course_name, ects, undergraduate)
values ('Αστροφυσική Πλάσματος','3',true),('Παρατηρησιακή Αστροφυσική','2',false),('Γενική θεωρία Σχετικότητας','3',true),
('Δυναμική ρευστών','1',false),('Παρατηρησιακή Αστροφυσική','3',true),
('Στοιχειώδη Σωμάτια','3',true),('Ηλεκτρονικά Κυκλώματα','3',true),('Σήματα και Συστήματα','2',true)
,('Αλληλεπίδραση Φωτονίων με την ύλη','3',false),('Κβαντομηχανική','1',true)
,('Ατομική Φυσική','3',true),('Μικροηλεκτρονική','2',false);



-- pub_category
select * from pub_category;

insert into pub_category(category_name)
values('Ηλεκτρονική φυσική'),('Αστροφυσική'),('Πυρινική Φυσική');

select * from pub_category;



-- lab_members
select * from lab_members;

insert into lab_members(role_id,member_name,sir_name, email,short_cv,web_page)
values(1,'Απόστολος','Μαστιχιάδης','mastich@gmail.com','Ο Απόστολος Μαστιχιάδης είναι καθηγητής του τμήματος φυσικής και υπεύθυνος του Μεταπτυχιακού προγράμματος σπουδών Αστροφυσικής.','www.mastich.com'),
(2 ,'Θεοχάρης','Αποστολάτος','apostol@phys.uoa.gr','Αναπληρωτής καθηγητής του φυσικού αθήνας. Διδακτορική διατριβή στο μετανευτώνιο όριο στον τομέα της γενικής σχετικότητας','www.apostol.com'),
( 8,'Ορέστης','Κατσαρός','katsar@phys.uoa.gr', null , null),
( 7,'Μαρκέλλα','Παππαιωάνου','mark@phys.uoa.gr','Aπόφοιτος του τμήματος Φυσικής της Κρήτης και μεταπτυχιακός φοιτητής του ΠΜΣ Πυρινικής φυσικής του τμήματος Φυσικής Αθήνας. Έχει συμμετάσχει το καλοκαίρι του 2015 στο πρόγραμμα Erasmus+ στο ερευνητικό εργαστήριο του cern ','www.mark.com');
 insert into lab_members(role_id,member_name,sir_name,email)
 values(1,'Δημήτρης','Τσαγκέτας','tsagketsa@phys.uoa.com');




-- research_details
select * from research_details;

insert into research_details(program_name,ongoing)
values('Laser Interferometer Gravitational-wave Observatory',true),
('Event Horizon Telescope',false),
('Photon Interactions with High Energy Particles',false),
('Fiber Optic Communication Systems',false),
('Optical Wireless Communication Systems',true),
('Direct and indirect detection of dark matter',false);

-- institutes
select * from institutes;

insert into institutes(institute_name)
values('MIT'),('Stanford'),('NASA'),('CERN');

-- co_authors
select * from co_authors;

insert into co_authors(cauthor_name)
values('Mckee'),('Einstein'),('Maxwell'),('Bolzano'),('Tesla');


-- publications
select * from publications;

insert into publications(category_id,pub_title,date_of_pub,publisher,publish_at_conference, co_author_foreign)
values( 1,'“Average capacity of optical wireless communication systems over atmospheric turbulence channels','2009-05-08','Journal of Lightwave Technology',false,false),
( 2,'Electron-Positron Pair Production by Ultrarelativistic Electrons in a Soft Photon Field','2020-03-06','Astrophysical Journal',false,true),
(1,'On the use of Wavelength and Time Diversity in Optical Wireless Communication Systems over Gamma-Gamma Turbulence Channels','2018-12-05','Journal of Optics & Laser Technology',false,false),
( 2,'Gravitational waves','2019-06-08','Big Ideas and Thoughts of Astronomy, Astrophysics and Space Science',true,true),
( 3,'Tevatron constraints on models of the Higgs boson with exotic spin and parity','2018-01-02','Physics Today',false,false),
( 3,'First search for exotic Z boson decays into photons and neutral pions in hadron collisions','2021-05-08','Physics Today', false,true),
( 2,'Electromagnetic Cascades in the Magnetosphere of a Very Young Pulsar','2021-12-12','Astrophysical Journal',false,false);




-- announcements
select * from announcements;


insert into announcements(member_id,course_id,title,detail,date_of_announcement)
values(1,null,'Συνταξιοδότηση του καθηγητή κύριου Μαστιχιάδη','Θα πραγματοποιηθεί το Σάββατο στις 6 η ώρα το απόγευμα δεξίωση αποχαιρετισμού του καθηγητη κύριου Μαστιχιάδη στην αίθουσα δεξιώσεων του εργαστηρίου','2022-02-21'),
(null,1,'Αλλαγή διάλεξης','Το μάθημα της παρασκευής αναβάλλεται για την επόμενη εβδομάδα όπου θα πραγματοποιηθεί Τρίτη στις 4 η ωρα','2022-02-20'),
(2,3,'Αλλαγή υπεύθυνου μαθήματος','Την διδασκαλία του μαθήματος αναλαμβάνει ο καθηγητής κύριος Αποστολάτος και οι ώρες θα ανακοινωθούν εντός των επόμενων ημερών','2022-02-19'),
(null,null,'Δήλωση μαθημάτων','Ξεκίνησαν οι δηλώσεις μαθημάτων. Η προθεσμία δηλώσεων λήγει στις 15 φλεβάρη','2022-02-06');


select * from announcements;



-- co_write
select * from co_write;

insert into co_write(pub_id,cauthor_id)
values(2,1),(6,2),(6,3);
insert into co_write(pub_id,cauthor_id)
values(4,3),(4,4),(6,5);


-- publish
select * from publish;

insert into publish(member_id,pub_id)
values(1,7),(1,6),(4,6),(2,4);
insert into publish(pub_id,member_id)
values(2,1),(2,2),(3,5),(5,1),(3,1);
insert into publish(pub_id,member_id)
values(1,5);
select * from lab_members;

-- research

insert into research(member_id,research_id)
values(1,2),(2,3),(4,1),(5,6),(3,4),(4,5);

-- took_place
select * from took_place;

insert into took_place(research_id,institute_id)
values(1,1),(1,2),(2,4),(5,2),(6,1);
insert into took_place(research_id,institute_id)
values(1,3),(3,1);


-- courses

insert into teach(member_id,course_id,hours_for_each)
values(1,1,2),(1,2,2),(2,3,3),(5,6,2),(4,4,2),(4,8,2),(3,5,3),(3,7,2),(2,9,3);

insert into teach(member_id,course_id,hours_for_each)
values(2,1,2),(3,2,2),(3,3,3),(4,6,2),(5,4,2);