package com.example.demo.entity;

import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Date;

@Entity
@Data
@Table(name = "t_goodytpe")
public class GoodsType {

    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid")

    @Column(name = "goodsId",length = 32,nullable = false)
    private String goodsId;

    @Column(name = "type",length = 200,nullable = false,unique = true)
    private String type;

    @Column(name = "CreTime")
    private Date CreTime;

    @Column(name = "UpTime")
    private Date UpTime;


}
