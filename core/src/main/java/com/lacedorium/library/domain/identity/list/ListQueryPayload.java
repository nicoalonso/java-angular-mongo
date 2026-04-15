package com.lacedorium.library.domain.identity.list;

import lombok.Data;

@Data
public class ListQueryPayload {
    String id;
    String createdBy;
    String fromCreatedAt;
    String toCreatedAt;
    String updatedBy;
    String fromUpdatedAt;
    String toUpdatedAt;
    Integer page;
    Integer limit;
    String sort;
}
