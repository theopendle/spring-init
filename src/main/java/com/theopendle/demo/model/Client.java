package com.theopendle.demo.model;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDate;

@Getter
@Setter
public class Client implements Serializable {
    private static final long serialVersionUID = -6673532093586278058L;

    private String firstName;
    private String lastName;
    private LocalDate dob;
}
