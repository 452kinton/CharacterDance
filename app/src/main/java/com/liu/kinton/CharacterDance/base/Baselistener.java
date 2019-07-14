package com.liu.kinton.CharacterDance.base;

import android.view.ViewGroup;

interface BaseListener{

    /**
     * Is the position or the data suits for this OneListener?
     * @param position the data's position int the list
     * @param o the data
     * @return true/false
     */
    boolean isMyItemViewType(int position, Object o);

    /**
     * Create a ViewHolder for this OneListener
     * @param parent RecyclerView
     * @return OneViewHolder
     */
    BaseViewHolder getMyViewHolder(ViewGroup parent);
}