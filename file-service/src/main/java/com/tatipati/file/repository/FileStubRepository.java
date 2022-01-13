package com.tatipati.file.repository;

import com.tatipati.file.model.FileStub;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface FileStubRepository extends CrudRepository<FileStub,Long>  {
    public List<FileStub> findByTitle(String title);
    public FileStub findByCreateAt(Date createAt);
}
