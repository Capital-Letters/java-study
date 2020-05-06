class Chess_pieces(object) :                                        #棋子类
    def __init__(self , color , position=(-1,-1) ):
        self.__color = color
        self.__position = position
    def set_color (self , color) :
        self.__color = color
    def set_position(self , position):
        self.__position = position
    def get_position(self):
        return self.__position
    def get_color(self):
        return self.__color
class Chess_board(object) :                                 #棋盘类
    board_list = [[],[],[],[],[],[],[],[],[],[]]
    for x in range(10):
        for y in range(10):
            board_list[x].append(0)
    def __init__(self):
        self.__board_list = Chess_board.board_list
    def set_chess_piece_on_chess_board(self,Chess_pieces):
        x = Chess_pieces.get_position()[0] - 1
        y = Chess_pieces.get_position()[1] - 1
        chess_board = Chess_board.get_chess_board(self)
        chess_board[x][y] = Chess_pieces.get_color()
        for item in chess_board :
            print(item)
    def get_chess_board(self):
        return self.__board_list
    def clean_chess_board(self):
        cleaned_board = [[],[],[],[],[],[],[],[],[],[]]
        for x in range(10):
            for y in range(10):
                cleaned_board[x].append(0)
        return cleaned_board
class History_position(object) :                            #记录玩家放置位置历史的历史位置类
    History_player1 = []
    History_player2 = []
    def remember_the_player1_history_position(self,Chess_pieces):             #记录玩家1历史放置位置
        History_position.History_player1.append(Chess_pieces.get_position())
        return History_position.History_player1
    def remember_the_player2_history_position(self,Chess_pieces):               #记录玩家2历史放置位置
        History_position.History_player2.append(Chess_pieces.get_position())
        return History_position.History_player2
class Rule(object) :                                                    #规则类
    def __init__(self , Chess_board):
        self.__Chess_board = Chess_board
    def player1_is_winner(self , History_position) :                    #判断玩家一是否是赢家
        history_player1 = History_position.History_player1
        player1_win_flag = 0
        #
        for x in range(len(history_player1)):
            if (history_player1[x][0] + 1, history_player1[x][1] + 1) in history_player1 and (
            history_player1[x][0] + 2, history_player1[x][1] + 2) in history_player1 and (
            history_player1[x][0] + 3, history_player1[x][1] + 3) in history_player1 and (
            history_player1[x][0] + 4, history_player1[x][1] + 4) in history_player1:
                player1_win_flag = 1
                break
            if (history_player1[x][0] , history_player1[x][1] + 1) in history_player1 and (
                    history_player1[x][0] , history_player1[x][1] + 2) in history_player1 and (
                    history_player1[x][0] , history_player1[x][1] + 3) in history_player1 and (
                    history_player1[x][0] , history_player1[x][1] + 4) in history_player1:
                player1_win_flag = 1
                break
            if (history_player1[x][0] + 1, history_player1[x][1] ) in history_player1 and (
                    history_player1[x][0] + 2 , history_player1[x][1] ) in history_player1 and (
                    history_player1[x][0] + 3 , history_player1[x][1] ) in history_player1 and (
                    history_player1[x][0] + 4 , history_player1[x][1] ) in history_player1:
                player1_win_flag = 1
                break
        if player1_win_flag == 1 :
            return True
        else:
            return False
    def player2_is_winner(self,History_position):                   #判断玩家二是否是赢家
        history_player2 = History_position.History_player2
        player2_win_flag = 0
        # 行列取胜
        for x in range(len(history_player2)):
            if (history_player2[x][0] + 1, history_player2[x][1] + 1) in history_player2 and (
                    history_player2[x][0] + 2, history_player2[x][1] + 2) in history_player2 and (
                    history_player2[x][0] + 3, history_player2[x][1] + 3) in history_player2 and (
                    history_player2[x][0] + 4, history_player2[x][1] + 4) in history_player2:
                player2_win_flag = 1
                break
            if (history_player2[x][0] , history_player2[x][1] + 1) in history_player2 and (
                    history_player2[x][0] , history_player2[x][1] + 2) in history_player2 and (
                    history_player2[x][0] , history_player2[x][1] + 3) in history_player2 and (
                    history_player2[x][0] , history_player2[x][1] + 4) in history_player2:
                player2_win_flag = 1
                break
            if (history_player2[x][0] + 1, history_player2[x][1] ) in history_player2 and (
                    history_player2[x][0] + 2 , history_player2[x][1] ) in history_player2 and (
                    history_player2[x][0] + 3 , history_player2[x][1] ) in history_player2 and (
                    history_player2[x][0] + 4 , history_player2[x][1] ) in history_player2:
                player2_win_flag = 1
                break
        if player2_win_flag == 1:
            return True
        else:
            return False
    def game_over(self,Chess_board):                #判断游戏是否结束
        flag_game_over = 1
        chess_board = Chess_board.get_chess_board()
        for x in range(len(chess_board)) :
            for y in range(len(chess_board[x])) :
                if chess_board[x][y] == 0 :
                    flag_game_over = 0
        if flag_game_over == 0 :
            return False
        else:
            return True

if __name__ == "__main__" :             #游戏开始
    while True :
        chess_board = Chess_board()                         #创建棋盘对象
        history_position = History_position()               #创建历史放置位置的对象
        rule = Rule(chess_board)
        the_position_of_player1 = input('请玩家一输入一个坐标')   #玩家一输入棋子的参数
        x1 = int(the_position_of_player1.split(',')[0])
        y1 = int(the_position_of_player1.split(',')[1])
        while True :
            if (x1,y1) in history_position.History_player1 or (x1 ,y1) in history_position.History_player2 or x1 >= 11 or x1< 1 or y1 >= 11 or y1< 1  :
                the_position_of_player1 = input('请玩家一重新输入一个坐标')
                x1 = int(the_position_of_player1.split(',')[0])
                y1 = int(the_position_of_player1.split(',')[1])
            else:
                break
        first_player = Chess_pieces(1,(x1,y1))                                      #创建棋子对象
        history_position.remember_the_player1_history_position(first_player)        #记录玩家一的历史放置位置
        chess_board.set_chess_piece_on_chess_board(first_player)                    #将棋盘对象上放入玩家一的棋子
        if rule.player1_is_winner(history_position) :                               #判断玩家一是否是赢家
            print('player1 is winner')
            break
        print('***********')
        the_position_of_player2 = input('请玩家二输入一个坐标')                    #玩家二输入
        x2 = int(the_position_of_player2.split(',')[0])
        y2 = int(the_position_of_player2.split(',')[1])
        while True:
            if (x2, y2) in history_position.History_player1 or (x2 , y2) in history_position.History_player2 or x2 > 10 or x2< 1 or y2 > 10 or y2< 1 :
                the_position_of_player2 = input('请玩家二重新输入一个坐标')
                x2 = int(the_position_of_player2.split(',')[0])
                y2 = int(the_position_of_player2.split(',')[1])
            else:
                break
        second_player = Chess_pieces(2, (x2,y2))                                    #创建棋子对象二
        history_position.remember_the_player2_history_position(second_player)       #记录玩家二的操作
        chess_board.set_chess_piece_on_chess_board(second_player)
        if rule.player2_is_winner(history_position) :                               #判断玩家二是否是赢家
            print('player2 is winner')
            break
        if rule.game_over(chess_board) :                                            #判断游戏是否结束
            print('game over and no one is winner')
            flag_again = int(input('if you want play the chess again , please press the key 1'))
            if flag_again == 1 :
                chess_board.clean_chess_board()
            else:
                break