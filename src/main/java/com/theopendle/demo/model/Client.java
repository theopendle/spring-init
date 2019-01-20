package com.theopendle.demo.model;

import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

@Accessors(chain = true)
@Data
public class Client implements Serializable {
    private static final long serialVersionUID = -6673532093586278058L;

    private Long id;
    private String firstName;
    private String lastName;
}
