create table customer(id LONG, name varchar2(50));
create table transaction(id LONG, custid LONG,transdtm timestamp,transamt decimal);