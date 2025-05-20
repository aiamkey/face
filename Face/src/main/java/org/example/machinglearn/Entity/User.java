package org.example.machinglearn.Entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import org.springframework.data.annotation.Id;

import java.io.File;
import java.io.Serializable;

/**
 * @author 张宇
 * @version 1.0
 * @date 2025/4/29 22:20
 */


@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@Table(name = "user", uniqueConstraints = {
        @UniqueConstraint(columnNames = "phone")  //确保手机号唯一
})
public class User implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String username;
    private String password;
    private String phone;
    private Integer age;
    private String gender;

    @Lob
    @Column(columnDefinition = "LONGBLOB")
    private byte[] avatar;



}
