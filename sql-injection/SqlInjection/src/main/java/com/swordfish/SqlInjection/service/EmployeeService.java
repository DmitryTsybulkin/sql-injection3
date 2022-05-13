package com.swordfish.SqlInjection.service;

import com.swordfish.SqlInjection.model.Employee;
import com.swordfish.SqlInjection.model.Role;
import com.swordfish.SqlInjection.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.PostConstruct;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.Instant;
import java.util.List;

@Service
public class EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;
    @Autowired
    private EntityManager entityManager;
    private final Path root = Paths.get("tmp");

    @PostConstruct
    public void init() {
        employeeRepository.save(new Employee("Denis Kondratyev", "kfgkgkm", Role.MANAGER));
        employeeRepository.save(new Employee("Valeria Lukshina ", "addscsl", Role.MANAGER));
        employeeRepository.save(new Employee("Mikhail Lushin", "123hjnc", Role.MANAGER));
        employeeRepository.save(new Employee("Viacheslav Petrovich", "DAS{Ldk", Role.MANAGER));
        employeeRepository.save(new Employee("Pavel Volkov", "7yhjkfd", Role.MANAGER));
        employeeRepository.save(new Employee("Nikita Usov", "987jkmddh", Role.MANAGER));
        employeeRepository.save(new Employee("Svetlana Stasova", "Ukj{Ldk", Role.MANAGER));
        employeeRepository.save(new Employee("ctf{FINAL_SQL_COMPLETE}", "Ukj{Ldk", Role.MANAGER));

        test();
    }

    public List<String> getEmployeesByName(String name) {
        String sql = "select name from employee where name = :name";
        TypedQuery<String> q = entityManager.createQuery(sql, String.class).setParameter("name", name);
        return q.getResultList();
    }

    public List<Employee> getEmployees(){
        return entityManager
                .createNativeQuery("select * from employee where role='MANAGER'", Employee.class)
                .getResultList();
    }

    public void uploadFile(MultipartFile multipartFile) throws IOException {
        Path path = root.resolve(multipartFile.getOriginalFilename() + "_" + Instant.now());
        try {
            Files.copy(multipartFile.getInputStream(), path);
        } catch (Exception e) {
            e.printStackTrace();
        }
        BufferedReader bufferedReader = new BufferedReader(new FileReader(path.toString()));
        String curLine;
        while ((curLine = bufferedReader.readLine()) != null){
            entityManager.createNativeQuery(
                    "insert into employee '" + curLine + "'", Employee.class
            );
            System.out.println(curLine);
        }
        bufferedReader.close();
    }

        public void test() {
        List<Employee> employees = getEmployeesByName("Sergey Zhdanov");
        assert employees.size() == 1;
        System.out.println("Success");
    }
}