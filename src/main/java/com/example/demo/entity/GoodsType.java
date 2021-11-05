package com.example.demo.entity;

import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Data
@Table(name = "t_goods_type")
public class GoodsType {

    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid")

    @Column(name = "goods_type_id",length = 32,nullable = false)
    private String goodsTypeId;

    @Column(name = "type",length = 200,nullable = false,unique = true)
    private String type;

    @Column(name = "cre_time")
    private Date CreTime;

    @Column(name = "upd_time")
    private Date updTime;

    @OneToMany(cascade = {CascadeType.ALL})
    @JoinColumn(name = "goods_type_id")
    private List<Goods> goodsList;


}
