/*
 * Copyright (c) 2002-2015, Mairie de Paris
 * All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions
 * are met:
 *
 *  1. Redistributions of source code must retain the above copyright notice
 *     and the following disclaimer.
 *
 *  2. Redistributions in binary form must reproduce the above copyright notice
 *     and the following disclaimer in the documentation and/or other materials
 *     provided with the distribution.
 *
 *  3. Neither the name of 'Mairie de Paris' nor 'Lutece' nor the names of its
 *     contributors may be used to endorse or promote products derived from
 *     this software without specific prior written permission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS"
 * AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE
 * IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE
 * ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT HOLDERS OR CONTRIBUTORS BE
 * LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR
 * CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF
 * SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS
 * INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN
 * CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE)
 * ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE
 * POSSIBILITY OF SUCH DAMAGE.
 *
 * License 1.0
 */
package fr.paris.lutece.plugins.workflow.modules.formlimit.business;

import fr.paris.lutece.plugins.workflow.utils.WorkflowUtils;
import fr.paris.lutece.plugins.workflowcore.business.config.ITaskConfigDAO;
import fr.paris.lutece.util.sql.DAOUtil;


/**
 *
 * @author fallphenix
 *
 * * Une classe MyTaskConfigDAO implémentant
 * l'interface ITaskConfigDAO de la librairie
 * library-workflow-core et stockant en base de
 * données les propriétés de l'objet TaskConfig
 */
public class TaskFormLimitConfigDAO implements ITaskConfigDAO<TaskFormLimitConfig>
{
    private static final String SQL_QUERY_FIND_BY_PRIMARY_KEY = "SELECT id_task,number" +
        " FROM workflow_task_formlimit_config  WHERE id_task=?";
    private static final String SQL_QUERY_INSERT = "INSERT INTO  workflow_task_formlimit_config  " +
        "(id_task,number)VALUES(?,?)";
    private static final String SQL_QUERY_UPDATE = "UPDATE workflow_task_formlimit_config " + "SET id_task=?,number=?" +
        " WHERE id_task=?";
    private static final String SQL_QUERY_DELETE = "DELETE FROM workflow_task_formlimit_config  WHERE id_task=? ";

    /**
     * {@inheritDoc}
     */
    @Override
    public synchronized void insert( TaskFormLimitConfig config )
    {
        DAOUtil daoUtil = new DAOUtil( SQL_QUERY_INSERT, WorkflowUtils.getPlugin(  ) );

        int nPos = 0;

        daoUtil.setInt( ++nPos, config.getIdTask(  ) );
        daoUtil.setInt( ++nPos, config.getNumber(  ) );

        daoUtil.executeUpdate(  );
        daoUtil.free(  );
    }

    /**
    * {@inheritDoc}
    */
    @Override
    public void store( TaskFormLimitConfig config )
    {
        DAOUtil daoUtil = new DAOUtil( SQL_QUERY_UPDATE, WorkflowUtils.getPlugin(  ) );

        int nPos = 0;

        daoUtil.setInt( ++nPos, config.getIdTask(  ) );

        daoUtil.setInt( ++nPos, config.getNumber(  ) );
        daoUtil.setInt( ++nPos, config.getIdTask(  ) );
        daoUtil.executeUpdate(  );
        daoUtil.free(  );
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TaskFormLimitConfig load( int nIdState )
    {
        TaskFormLimitConfig config = null;
        DAOUtil daoUtil = new DAOUtil( SQL_QUERY_FIND_BY_PRIMARY_KEY, WorkflowUtils.getPlugin(  ) );

        daoUtil.setInt( 1, nIdState );

        daoUtil.executeQuery(  );

        int nPos = 0;

        if ( daoUtil.next(  ) )
        {
            config = new TaskFormLimitConfig(  );
            config.setIdTask( daoUtil.getInt( ++nPos ) );
            config.setNumber( daoUtil.getInt( ++nPos ) );
        }

        daoUtil.free(  );

        return config;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void delete( int nIdState )
    {
        DAOUtil daoUtil = new DAOUtil( SQL_QUERY_DELETE, WorkflowUtils.getPlugin(  ) );
        daoUtil.setInt( 1, nIdState );
        daoUtil.executeUpdate(  );
        daoUtil.free(  );
    }
}
