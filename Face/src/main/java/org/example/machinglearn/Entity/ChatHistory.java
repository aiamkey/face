package org.example.machinglearn.Entity;

/**
 * @author 张宇
 * @version 1.0
 * @date 2025/4/29 21:25
 */

import com.baomidou.mybatisplus.annotation.*;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import jakarta.persistence.Column;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * @author 张宇
 * @version 1.0
 * @date 2025/4/29 22:20
 */

@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@Table(name = "chat_history")
public class ChatHistory implements Serializable {

    private static final long serialVersionUID = 1L;

    // 主键ID（自增策略）
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 用户ID（关联用户表）
     */
    @Column(name = "userid")
    private Integer userid;

    /**
     * 用户输入的提示内容
     */
    private String prompt;

    /**
     * AI生成的响应内容
     */
    private String response;

    /**
     * 对话时间（格式：yyyy-MM-dd HH:mm:ss）
     */
    // 自动获取时间，插入时填充
    @TableField(fill = FieldFill.INSERT_UPDATE)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    private LocalDateTime timestamp;

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }

    public String getResponse() {
        return response;
    }

    public void setResponse(String response) {
        this.response = response;
    }

    public String getPrompt() {
        return prompt;
    }

    public void setPrompt(String prompt) {
        this.prompt = prompt;
    }

    public Integer getUserid() {
        return userid;
    }

    public void setUserid(Integer userid) {
        this.userid = userid;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
