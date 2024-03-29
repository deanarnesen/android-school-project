package com.bignerdranch.android.shareddata.commands.commandSupport;

public enum CommandType {
    ADD_CURRENT_LOBBY_MEMBERS, ADD_GAME_LISTING, ADD_INITIAL_GAMES, ADD_PLAYER_TO_LOBBY,
    COMMAND_LIST, DISPLAY_ERROR_MESSAGE, INCREMENT_GAME_PLAYER_COUNT, REMOVE_GAME_LISTING,
    CREATE_GAME_COMMAND, DISTRIBUTE_CHAT_MESSAGE, GET_COMMAND_LIST, JOIN_GAME_COMMAND,
    LEAVE_GAME, LOGIN_COMMAND, REGISTER_COMMAND, START_GAME_COMMAND, ENTER_GAME, DECREMENT__GAME_PLAYER_COUNT,
    ADD_TRAIN_CARD, UPDATE_FACE_UP_TRAIN_CARDS, UPDATE_SCORE, UPDATE_TRAIN_CARDS_COUNT,
    UPDATE_TRAIN_CARS, UPDATE_TURN, UPDATE_TRAIN_DECK_COUNT, UPDATE_DEST_CARD_COUNT,
    UPDATE_DEST_DECK_COUNT, ADD_DESTINATION_CARD, DRAW_DESTINATION_CARD, SET_ROUTES,
    RETURN_CHOSEN, DRAW_TRAIN_CARD, CLAIM_ROUTE, PASS_TURN, ADD_ROUTE, LAST_ROUND, MOVE_TO_END_GAME_SCREEN,
    DISTRIBUTE_FINAL_SCORE, UPDATE_FINAL_SCORES
}
