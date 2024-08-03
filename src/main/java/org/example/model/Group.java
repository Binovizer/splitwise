package org.example.model;

import lombok.Builder;
import lombok.Data;

import java.util.List;

/**
 * The type Group
 *
 * @author nadeem
 * Date : 03/08/24
 */
@Data
@Builder
public class Group {
    private String id;
    private String name;
    private String description;
    private List<User> members;
}
