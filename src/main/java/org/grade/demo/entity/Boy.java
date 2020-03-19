package org.grade.demo.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedAttributeNode;
import javax.persistence.NamedEntityGraph;
import javax.persistence.NamedSubgraph;
import javax.persistence.Table;

import org.grade.core.persistence.AbstractDynamicEntity;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "boy")
@Getter
@Setter
@NamedEntityGraph(name = "graph.boy.all", attributeNodes = {
        @NamedAttributeNode("id"),
        @NamedAttributeNode("nom"),
        @NamedAttributeNode("description"),
        @NamedAttributeNode(value = "girl", subgraph = "girl-san-boy")
}, subgraphs = {
        @NamedSubgraph(name = "girl-san-boy", attributeNodes = {
                @NamedAttributeNode("id"),
                @NamedAttributeNode("nom")
        })
})
@NamedEntityGraph(name = "graph.boy.row", attributeNodes = {
        @NamedAttributeNode("id"),
        @NamedAttributeNode("nom"),
        @NamedAttributeNode("description")
})
public class Boy extends AbstractDynamicEntity
{

    @Column(name = "nom", length = 40, columnDefinition = "Nom de la boy", nullable = false)
    private String nom;

    @Column(name = "description", length = 255, columnDefinition = "Description de la boy")
    private String description;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "girl_id")
    private Girl girl;

}
