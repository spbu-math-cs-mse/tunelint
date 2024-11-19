package org.goalteam.tunelint.model.changerequest

interface PersistentRequest {
    val directRequest: ChangeRequest

    val reverseRequest: ChangeRequest
}
