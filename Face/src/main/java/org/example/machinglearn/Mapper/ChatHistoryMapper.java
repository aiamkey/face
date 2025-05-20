package org.example.machinglearn.Mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.example.machinglearn.Entity.ChatHistory;
import java.util.ArrayList;

/**
 * @author 张宇
 * @version 1.0
 * @date 2025/4/29 22:20
 */

@Mapper
public interface ChatHistoryMapper extends BaseMapper<ChatHistory> {

    @Insert("INSERT INTO chat_history (userid, prompt, response) VALUES (#{userid},#{prompt},#{response})")
    void addChatHistory(ChatHistory chatHistory);

    //查看对话记录
    @Select("SELECT * FROM chat_history WHERE userid = #{userid}")
    ArrayList<ChatHistory> getChatHistory(int userid);


}
