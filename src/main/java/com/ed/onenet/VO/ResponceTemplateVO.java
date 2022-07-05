package com.ed.onenet.VO;

import com.ed.onenet.dto.UserDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResponceTemplateVO {

    private UserDTO userDTO;
    private Menu menu;

}
