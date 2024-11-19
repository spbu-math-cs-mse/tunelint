package org.goalteam.tunelint.model.changerequest

interface RequestableMelody :
    SubscribableMelody,
    Notifiable<ChangeRequest>
