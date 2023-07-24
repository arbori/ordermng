package com.ordermng.core.order;

import java.util.ArrayList;
import java.util.List;

class BasicDTO {
    public Long getId() {
        return 1L;
    }
    public void setId() {
        // Test
    }

    public String getWord() {
        return "word";
    }
    public void setWord() {
        // Test
    }
}

class BasicEntity extends BasicDTO {
    public Long getId() {
        return super.getId();
    }

    public String getWord() {
        return super.getWord();
    }
}

class ComposeDTO {
    public Long getId() {
        return 1L;
    }
    public void setId() {
        // Test
    }

    private BasicDTO basicDTO;
    public BasicDTO getBasicDTO() {
        return basicDTO;
    }
    public void setBasicDTO(BasicDTO basicDTO) {
        this.basicDTO = basicDTO;
    }

    private List<BasicDTO> basicDTOList;
    public List<BasicDTO> getBasicDTOList() {
        return basicDTOList;
    }
    public void setBasicDTOList(List<BasicDTO> basicDTOList) {
        this.basicDTOList = basicDTOList;
    }
}

class ComposeEntity extends ComposeDTO {
    public Long getId() {
        return super.getId();
    }

    public BasicEntity getBasicEntity() {
        return (BasicEntity) getBasicDTO();
    }
    public void setBasicEntity(BasicEntity basicEntity) {
        super.setBasicDTO(basicEntity);
    }

    public void add(BasicEntity basicEntity) {
        super.getBasicDTOList().add(basicEntity);
    }
    public BasicEntity get(int index) {
        return (BasicEntity) super.getBasicDTOList().get(index);
    }
    public List<BasicEntity> getBasicEntities() {
        List<BasicEntity> result = new ArrayList<>();
        super.getBasicDTOList().forEach(b -> result.add((BasicEntity) b));
        return result;
    }
}


public class ComposeCheck {
    
}
