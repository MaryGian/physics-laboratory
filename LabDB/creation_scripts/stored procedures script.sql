use laboratory;
delimiter $$
create procedure ResearchOfCouples()
begin
/* returns couples which has common research program name and the program name*/
select r2.research_id,rd.program_name, r1.member_id,lm1.sir_name, r2.member_id,lm2.sir_name from research r1, research r2,
lab_members lm1, lab_members lm2, research_details rd
where r1.research_id=r2.research_id and r1.member_id != r2.member_id
and lm1.member_id=r1.member_id and lm2.member_id=r2.member_id and r2.research_id=rd.research_id
group by r2.research_id
order by r2.research_id;
end; $$
delimiter ; 


delimiter $$
create procedure PublicationsBooleanConferenceAndYear(in atConference boolean, in whatYear YEAR)
begin
/* returns publications that has\hasn't publish at conference and specific year*/
select lm.member_id, lm.sir_name, pub.pub_id, c.category_name, pub.pub_title, pub.date_of_pub, pub.publisher from lab_members lm, publish p, publications pub, pub_category c 
where lm.member_id=p.member_id and p.pub_id=pub.pub_id and pub.category_id=c.category_id and  pub.publish_at_conference=atConference and YEAR(pub.date_of_pub)=whatYear;
end; $$
delimiter ;

call PublicationsBooleanConferenceAndYear(false,'2018');


/* δημοσιευσεις μέλους*/
delimiter $$
create procedure MembersPublications(in id int)
begin
select lm.sir_name, pub.pub_id, c.category_name, pub.pub_title, pub.date_of_pub, pub.publisher,pub.publish_at_conference from publications pub, pub_category c, publish p, lab_members lm
where pub.category_id=c.category_id and pub.pub_id=p.pub_id and p.member_id=lm.member_id
and lm.member_id=id
order by pub.date_of_pub desc;
end;$$
delimiter ;



delimiter $$
create procedure PublicationsOfCouples()
begin
/* returns couples which has common publications*/
select p2.pub_id,pub.pub_title, p1.member_id,lm1.sir_name, p2.member_id,lm2.sir_name from publish p1, publish p2,
lab_members lm1, lab_members lm2, publications pub
where p1.pub_id=p2.pub_id and p1.member_id != p2.member_id
and lm1.member_id=p1.member_id and lm2.member_id=p2.member_id and p2.pub_id=pub.pub_id
group by p2.pub_id
order by pub_id;
end; $$
delimiter ; 


delimiter $$
create procedure ClearEverything()
begin
/* for clear publications */
delete from publications where pub_id not in (select pub_id from publish) ;
delete from co_authors where cauthor_id not in (select cauthor_id from co_write);
delete from research_details where research_id not in (select research_id from research);
delete from institutes where institute_id not in (select institute_id from took_place);
end; $$
delimiter ;


delimiter $$
create procedure FindForeignCoAuthorsForPub(in id int)
begin  
	if ((select co_author_foreign from publications where pub_id=id)=1) then
		select p.pub_id,p.pub_title, c.cauthor_id, c.cauthor_name from co_authors c, co_write cw, publications p
		where p.pub_id=cw.pub_id and c.cauthor_id=cw.cauthor_id and cw.pub_id=id;
	end if;
        
end; $$
delimiter ;
use laboratory;
drop procedure FindForeignCoAuthorsForPub;
call FindForeignCoAuthorsForPub(2);

 
delimiter $$
create procedure TeachingCourses(in id int)
begin
	select c.course_name, lm.member_id,lm.sir_name, t.hours_for_each from lab_members lm, teach t, courses c
	where lm.member_id=t.member_id and t.course_id=c.course_id and c.course_id=id;
end; $$
delimiter ;

delimiter $$
create procedure AnnounceForMemberOrCourse(in whichOne varchar(10), in  id int)
begin
	if (whichOne='course') then
		select c.course_name, a.an_id, a.title, a.detail  from announcements a, courses c
		where a.course_id=c.course_id and c.course_id=id;
	elseif (whichOne='member') then
		select lm.sir_name, a.an_id, a.title, a.detail  from announcements a, lab_members lm
		where a.member_id=lm.member_id and lm.member_id=id;
	end if;
end; $$
delimiter ;
drop procedure AnnounceForMemberOrCourse;

call AnnounceForMemberOrCourse('member',1);


delimiter $$
create procedure NumberOfPublications()
begin
	select lm.member_id, lm.member_name, (select count(p.pub_id)  from publish p where p.member_id=lm.member_id group by member_id ) as number_of_publications
	from lab_members lm;

end; $$
delimiter ;


delimiter $$
create procedure InsertAllForResearch(in memberId int, in programName varchar(100), in ongoing boolean, in instituteName varchar(30))
begin
	declare r_id_after_insert int;
    declare id_inst_after int;
    declare whichInstitute int;

	insert into research_details(program_name,ongoing)
    values(programName,ongoing);
    SET r_id_after_insert = last_insert_id();
    insert into research(research_id, member_id)
    values(r_id_after_insert, memberId);
    
    if ((select count(*) from institutes where institute_name=instituteName) = 0) then
		insert into institutes(institute_name)
        values(instituteName);
        set id_inst_after= last_insert_id();
        insert into took_place(institute_id,research_id)
        values(id_inst_after,r_id_after_insert);
	else
		set whichInstitute= (select institute_id from institutes where institute_name=instituteName);
		insert into took_place(institute_id,research_id)
        values(whichInstitute,r_id_after_insert);
	end if;
end; $$
delimiter ;


delimiter $$
create procedure InsertPublications(in memberId int, in categoryId int,in pubTitle varchar(150), in publisher varchar(150), in datePub date, in conference boolean, in coauthor boolean)
begin
	declare last_pub_id int;
    
	insert into publications(category_id,pub_title,publisher,date_of_pub,publish_at_conference,co_author_foreign)
    values(categoryId,pubTitle,publisher,datePub,conference,coauthor);
    set last_pub_id=last_insert_id();
    insert into publish(member_id,pub_id)
    values(memberId, last_pub_id);

end; $$
delimiter ;

delimiter $$
create procedure InsertCoauthors(in public_id int,in coauthorName varchar(30))
begin
	declare id int;
	insert into co_authors(cauthor_name) values(coauthorName);
    set id=last_insert_id();
    insert into co_write(pub_id,cauthor_id) values(public_id,id);

end; $$
delimiter ;


delimiter $$
create procedure InsertInstitutes(in researchId int,in institute varchar(70))
begin
	declare id int;
	insert into institutes(institute_name) values(institute);
    set id=last_insert_id();
    insert into took_place(research_id,institute_id) values(researchId,id);

end; $$

