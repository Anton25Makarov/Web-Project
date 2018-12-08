package com.epam.repositpry;

import com.epam.model.Entity;
import com.epam.specification.SqlSpecification;

import java.util.List;
import java.util.Optional;

public interface Repository<T extends Entity> {
    List<T> query(SqlSpecification sqlSpecification);
    Optional<T> queryForSingleResult(SqlSpecification sqlSpecification);
}









//
//
//
//// репозитори фэктори{
//
////private connection
///*
//* constructor(){
//* connection pool.getconnection;
//*
//*
//* }
//* get userRepository
//*
//* pool.returnConnection
//* */
//
//
////
///*
//* try(factory = new repository*/
//
////connectionPool