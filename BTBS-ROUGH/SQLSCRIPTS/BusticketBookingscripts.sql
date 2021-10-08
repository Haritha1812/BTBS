
select * from customer
select * from bus
update seat_master set seat_status='NO' where bus_id=224 and seat_name ='Seat0'
select * from public.route
select * from seat
insert into seat_master values(1,'Seat1','Available',216)
delete from route_master where routeid  =209
delete from bus_master where id  =270
delete from seat_master where id  =1
insert into route values(1,550,'Cuddalore','CUD-PDY','Pondichery');
select * from passenger
select * from bookticket
insert into passenger_master values(5,43,'female','haeta',54,2)
insert into bus_master values(1,'10:00','AC','2010-09-03','13:00',321,'Trichy',21,'KVM',1)
insert into bookticket_master  values(3,1234,'2010-09-03','Confirmed',2,225,1);
select count(*) from seat_master where bus_id =225

select *from public.route 
drop table passenger ;
drop table customer ;
drop table bookticket;
drop table customer ;
drop table seat ;