drop database if EXISTS cafe_au__lait;
create database if not exists cafe_au__lait;
use cafe_au__lait;
create table if not exists Employee(
    EmpId varchar(20),
    Name varchar(30),
    Address text NOT NULL,
    NIC varchar(14) unique NOT NULL ,
    DOB Date NOT NULL ,
    JobTitle varchar(20) not null ,
    PhoneNumber varchar(15) unique not null ,
    Email varchar(50) ,
    CONSTRAINT PRIMARY KEY (EmpId)

    );

create table if not exists User(
    Username varchar(30),
    Password varchar(30) NOT NULL ,
    Email varchar(50) unique not null,
    JobTitle varchar(20) not null ,
    constraint primary key (Username)

    );

create table if not exists Customer(
    CustId varchar(30) ,
    CustName varchar(40) not null ,
    PhoneNumber varchar(15) not null ,
    Email varchar(50),
    constraint primary key (custId)

    );

create table if not exists Orders(
    OrderId varchar(20),
    CustId varchar(30) ,
    OrderDate DATE not null ,
    OrderTime TIME not null ,
    OrderPayment decimal(7,2) not null,
    Delivery varchar(20) not null ,
    primary key (OrderId),
    constraint foreign key (CustId) references Customer (CustId)
    on UPDATE cascade
    on delete cascade


    );

create table if not exists Delivery(
    DeliveryId varchar(20),
    DeliveryLocation varchar(25) not null,
    OrderId varchar(20),
    EmpId varchar(20),
    constraint primary key (DeliveryId),
    constraint foreign key (OrderId) references Orders(OrderId)
    on update cascade
    on delete cascade,

    constraint foreign key (EmpId) references Employee (EmpId)
    on update cascade
    on delete cascade




    );

create table if not exists Item(
    ItemId varchar(20),
    ItemName varchar(50) not null ,
    ItemQuantity INT not null,
    ItemUnitPrice decimal(7,2) not null ,
    ItemCategory varchar(30) ,
    constraint primary key (ItemId)
    );

create table if not exists supplierDTO(
    SupplierId varchar(20),
    SupplierName varchar(40) not null ,
    SupplierContact varchar(15) not null ,
    SupplierAddress text,
    SupplierEmail varchar(50),
    constraint primary key (SupplierId)
    );

create table if not exists Event(
    EventId varchar(20),
    EmpId varchar(20),
    EventName varchar(40) not null ,
    EventType varchar(40) not null ,
    EventDate DATE not null not null ,
    EventTime TIME not null not null ,
    constraint primary key (EventId),
    constraint foreign key (EmpId) references Employee(EmpId)
    on update cascade
    on delete cascade

    );

create table if not exists Salary(
    SalaryId varchar(20),
    EmpId varchar(20),
    SalaryPaymentMethod varchar(30) not null ,
    SalaryPayment decimal (8,2) not null ,
    SalaryOt decimal (7,2),
    constraint primary key (SalaryId),
    constraint foreign key (EmpId) references Employee(EmpId)
    on update cascade
    on delete cascade


    );

create table if not exists OrderDetail(
    OrderId varchar(20),
    ItemId varchar(20),
    OrderQuantity int,
    constraint primary key (OrderId,ItemId),
    constraint foreign key (OrderId) references Orders (OrderId)
    on update cascade
    on delete cascade ,

    constraint foreign key (ItemId)  references Item (ItemId)
    on update cascade
    on delete cascade

    );

create table if not exists SupplierLoadDetail(
    SupplierLoadId varchar(20),
    ItemId varchar(20),
    SupplierId varchar(20),
    SupplierQuantity int not null ,
    SupplierLoadTime TIME not null ,
    SupplierLoadDate  date not null ,
    SupplierLoadPricem decimal (8,2) not null ,
    constraint primary key (ItemId,SupplierId,SupplierLoadId),
    constraint foreign key (ItemId) references Item(ItemId)
    on update cascade
    on delete cascade ,

    constraint foreign key (SupplierId) references supplierDTO (SupplierId)
    on update cascade
    on delete cascade
    );

create table eventImages(
    EventId varchar(20),
    EventImage longblob not null,
        constraint foreign key (EventId) references event (EventId)
        on update cascade
        on delete cascade

);




