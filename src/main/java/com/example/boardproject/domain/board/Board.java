package com.example.boardproject.domain.board;

import com.example.boardproject.domain.BaseTimeEntity;
import com.example.boardproject.domain.reply.Reply;
import com.example.boardproject.domain.user.User;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

import javax.persistence.*;
import java.util.List;

@Builder
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Board extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 100)
    private String title;

    @Lob
    private String content;

    @Column
    private int count; //조회수

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "userId")
    private User user;

    //OneToMany의 기본 전력은 FetchType.LAZY이지만 reply를 반드시 가져올 것이기 때문에 EAGER로 지정해줌
    //mappdeBy: 연관관계의 주인이 아니다(난 FK가 아니다)DB에 칼럼을 만들지 마라. 나는 그냥 Board를 select할 때 조인문을 통해 값을 얻기 위해 필요한 것이다
    @OrderBy("id desc")
    @JsonIgnoreProperties({"board"})
    @OneToMany(mappedBy = "board", fetch = FetchType.EAGER, cascade = CascadeType.REMOVE)
    private List<Reply> replyList;

    public void update(String title, String content) {
        this.title = title;
        this.content = content;
    }
}
