package com.wubin.test.model;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@XStreamAlias("sUser")
public class User implements Serializable {

    @XStreamAlias("sId")
    private Integer id;

    @XStreamAlias("sName")
    private String name;

    @XStreamAlias("sBirth")
    private Date birth;

}
