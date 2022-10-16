package com.example.boardproject.domain.reply;

import com.example.boardproject.domain.BaseTimeEntity;
import com.example.boardproject.domain.board.Board;
import com.example.boardproject.domain.user.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Entity
public class Reply extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 500)
    private String content;

    @ManyToOne //Many:Reply, One:Board
    @JoinColumn(name = "boardId")
    private Board board; //Foreign key

    @ManyToOne //Many:Reply, One:User
    @JoinColumn(name = "userId")
    private User user;

    public void save(Board board, User user) {
        this.board = board;
        this.user = user;
    }
}
