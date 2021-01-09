/*
 * Copyright 2021 Root101 (jhernandezb96@gmail.com, +53-5-426-8660).
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Or read it directly from LICENCE.txt file at the root of this project.
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.root101.module.control.licence.repo.entity;

import com.root101.module.control.licence.repo.utils.ResourcesLicence;
import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Root101 (jhernandezb96@gmail.com, +53-5-426-8660)
 * @author JesusHdezWaterloo@Github
 */
@Entity
@Table(name = "licence", schema = ResourcesLicence.SCHEMA, uniqueConstraints = {
    @UniqueConstraint(columnNames = {"client_code"})})
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Licence.findAll", query = "SELECT l FROM Licence l"),
    @NamedQuery(name = "Licence.findByIdLicence", query = "SELECT l FROM Licence l WHERE l.idLicence = :idLicence"),
    @NamedQuery(name = "Licence.findByClientCode", query = "SELECT l FROM Licence l WHERE l.clientCode = :clientCode"),
    @NamedQuery(name = "Licence.findByLicence", query = "SELECT l FROM Licence l WHERE l.licence = :licence")})
public class Licence implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_licence", nullable = false)
    private Integer idLicence;

    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "client_code", nullable = false, length = 100)
    private String clientCode;

    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 500)
    @Column(name = "licence", nullable = false, length = 500)
    private String licence;

    public Licence() {
    }

    public Licence(Integer idLicence) {
        this.idLicence = idLicence;
    }

    public Licence(Integer idLicence, String clientCode, String licence) {
        this.idLicence = idLicence;
        this.clientCode = clientCode;
        this.licence = licence;
    }

    public Licence(String clientCode, String licence) {
        this.clientCode = clientCode;
        this.licence = licence;
    }

    public Integer getIdLicence() {
        return idLicence;
    }

    public void setIdLicence(Integer idLicence) {
        this.idLicence = idLicence;
    }

    public String getClientCode() {
        return clientCode;
    }

    public void setClientCode(String clientCode) {
        this.clientCode = clientCode;
    }

    public String getLicence() {
        return licence;
    }

    public void setLicence(String licence) {
        this.licence = licence;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idLicence != null ? idLicence.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Licence)) {
            return false;
        }
        Licence other = (Licence) object;
        if ((this.idLicence == null && other.idLicence != null) || (this.idLicence != null && !this.idLicence.equals(other.idLicence))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.root101.module.control.licence.repo.entity.Licence[ idLicence=" + idLicence + " ]";
    }

}
