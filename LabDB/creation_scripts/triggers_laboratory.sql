DELIMITER //
create trigger lab_members_check_email_web_page_update before  update on lab_members
for each row
begin
	if not new.email like '%@%' then
		signal sqlstate '45000'
        set message_text ='Wrong email';
	end if;
    if not new.web_page like 'www.%' then
		signal sqlstate '45000'
        set message_text ='You must insert a web-page url';
	end if;
    if new.sir_name='' then
		signal sqlstate '45000'
        set message_text ='The sir-name can not be an empty string';
	end if;
	
end//
DELIMITER ;




DELIMITER //
create trigger lab_members_check_email_web_page_insert before  insert on lab_members
for each row
begin
	if not new.email like '%@%' then
		signal sqlstate '45000'
        set message_text ='Wrong email';
	end if;
    if not new.web_page like 'www.%' then
		signal sqlstate '45000'
        set message_text ='You must insert a web-page url';
	end if;
    if new.sir_name='' then
		signal sqlstate '45000'
        set message_text ='The sir-name can not be an empty string';
	end if;
	
end//
DELIMITER ;


DELIMITER //
create trigger check_course_ects_insert before insert on courses for each row
begin
	if (new.ects>10 or new.ects<0) then
		signal sqlstate '45000'
        set message_text='The ects can not take a value more than ten or less than zero';
	end if;
end;//
DELIMITER ;

DELIMITER //
create trigger check_course_ects_update before update on courses for each row
begin
	if (new.ects>10 or new.ects<0) then
		signal sqlstate '45000'
        set message_text='The ects can not take a value more than ten or less than zero';
	end if;
end;//
DELIMITER ;

DELIMITER //
create trigger check_teach_hours_insert before insert on teach for each row
begin
	if (new.hours_for_each>10 or new.hours_for_each<0) then
		signal sqlstate '45000'
        set message_text='The hours per week can not take a value more than ten or less than zero';
	end if;
end;//
DELIMITER ;

DELIMITER //
create trigger check_teach_hours_update before update on teach for each row
begin
	if (new.hours_for_each>10 or new.hours_for_each<0) then
		signal sqlstate '45000'
        set message_text='The hours per week can not take a value more than ten or less than zero';
	end if;
end;+
DELIMITER ;

DELIMITER //
create trigger check_for_research_have_institute_bdelete before delete on took_place for each row
begin
	if ((select count(*) from took_place where research_id=old.research_id)<=1) then
		signal sqlstate '45000'
        set message_text='You can not delete this record because there is no other institute for this research.';
	end if;
end; //
DELIMITER ;

DELIMITER //
create trigger foreign_false_at_delete_of_cowrite_relations after delete on co_write for each row
begin
	if ((select count(*) from co_write where pub_id=old.pub_id)=0) then
		update publications
        set co_author_foreign=false
        where pub_id=old.pub_id;
        if((select count(*) from co_write where cauthor_id=old.cauthor_id)=0) then
			delete from co_authors where cauthor_id=old.cauthor_id;
		end if;
	end if;

end;//

delimiter //
create trigger update_pub_to_false_foreign_coauthor after update on publications for each row
begin
	if (new.co_author_foreign=true) then
		delete from co_write where pub_id=new.pub_id;
	end if;
end; //
delimiter ;


select * from announcements;

