package com.azxca1731.timeliner.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "role")
@NoArgsConstructor
@Getter
public class Role {

    @Id
    @Column(name = "role_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String roleType;

    @Builder
    public Role(String roleType) {
        this.roleType = roleType;
    }
}
