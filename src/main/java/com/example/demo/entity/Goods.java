package com.example.demo.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Date;

/**
 * @author Alex
 * @date 2021/11/5 9:48
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "t_goods")
public class Goods {

    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid")
    @Column(name = "goods_id", length = 32, nullable = false)
    private String goodsId;

    @Column(name = "goods_type_id", length = 32, nullable = false)
    private String goodsTypeId;

    @Column(name = "goods_name", length = 150, nullable = false)
    private String goodsName;

    @Column(name = "remain", length = 10, nullable = false)
    private Integer remain;

    @Column(name = "cre_time")
    private Date creTime;

    @Column(name = "upd_time")
    private Date updTime;

    @ManyToOne
    @JoinColumn(name = "goods_type_id", insertable = false, updatable = false)
    private GoodsType goodsType;
}
