package org.goalteam.tunelint.model.changerequest

import org.goalteam.tunelint.model.changerequest.impl.PersistenceManagerImpl
import org.goalteam.tunelint.model.changerequest.impl.RequestableMelodyImpl
import org.goalteam.tunelint.model.core.Melody

class PersistenceManagerFactory {
    fun persistenceManager(melody: Melody) = PersistenceManagerImpl(RequestableMelodyImpl(melody))
}
