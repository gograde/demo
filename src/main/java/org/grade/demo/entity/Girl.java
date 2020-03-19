package org.grade.demo.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.NamedAttributeNode;
import javax.persistence.NamedEntityGraph;
import javax.persistence.NamedSubgraph;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.grade.core.persistence.AbstractEntity;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "girl")
@Getter
@Setter
@NamedEntityGraph(name = "graph.girl.all", attributeNodes = {@NamedAttributeNode("id"),
        @NamedAttributeNode("nom"),
        @NamedAttributeNode(value = "boys", subgraph = "boy-san-girl")}, subgraphs = {
                @NamedSubgraph(name = "boy-san-girl", attributeNodes = {@NamedAttributeNode("id"),
                        @NamedAttributeNode("nom"), @NamedAttributeNode("description")})})
@NamedEntityGraph(name = "graph.girl.row", attributeNodes = {@NamedAttributeNode("id"),
        @NamedAttributeNode("nom")})
public class Girl extends AbstractEntity
{

    @Column(name = "nom", length = 40, columnDefinition = "Nom de la girl", nullable = false)
    private String nom;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "girl")
    private List<Boy> boys;

}
