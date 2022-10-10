package com.zhivaevartemsaveg.visual.context;

import com.zhivaevartemsaveg.visual.context.swing.DrawContextSwing;

public class DrawContextFactory {
    public static IDrawContext createContext() {
        return DrawContextSwing.create();
    }
}
