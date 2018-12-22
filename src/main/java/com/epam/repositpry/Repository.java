package com.epam.repositpry;

import com.epam.model.Entity;
import com.epam.specification.SqlSpecification;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public interface Repository<T extends Entity> {
    List<T> query(SqlSpecification sqlSpecification) throws SQLException;
    Optional<T> queryForSingleResult(SqlSpecification sqlSpecification) throws SQLException;
    boolean save(T entity);
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