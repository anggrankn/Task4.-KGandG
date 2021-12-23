package ru.vsu.cs.grankina_a_v;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import javax.swing.JPanel;

import ru.vsu.cs.grankina_a_v.functions.DefaultFunction;
import ru.vsu.cs.grankina_a_v.models.*;
import ru.vsu.cs.grankina_a_v.draw.IDrawer;
import ru.vsu.cs.grankina_a_v.draw.SimpleEdgeDrawer;
import ru.vsu.cs.grankina_a_v.screen.ScreenConverter;
import ru.vsu.cs.grankina_a_v.third.Camera;
import ru.vsu.cs.grankina_a_v.third.Scene;

/**
 *
 * @author Alexey
 */
public class DrawPanel extends JPanel implements CameraController.RepaintListener {
    private final Scene scene;
    private final ScreenConverter sc;
    private final Camera cam;

    public void setHelix(MySpiral helix) {
        scene.getModelsList().clear();
        scene.getModelsList().add(helix);
    }

    public DrawPanel() {
        super();
        sc = new ScreenConverter(-2, 2, 4, 4, 2, 2);
        cam = new Camera();
        CameraController camController = new CameraController(cam, sc);
        scene = new Scene(Color.WHITE.getRGB());
        scene.showAxes();

        MySpiral helix = new MySpiral(3, 6, 1f,
                0.1f, 10, new DefaultFunction());

        scene.getModelsList().add(helix);

        camController.addRepaintListener(this);
        addMouseListener(camController);
        addMouseMotionListener(camController);
        addMouseWheelListener(camController);
    }

    @Override
    public void paint(Graphics g) {
        sc.setScreenSize(getWidth(), getHeight());
        BufferedImage bi = new BufferedImage(getWidth(), getHeight(), BufferedImage.TYPE_INT_RGB);
        Graphics2D graphics = (Graphics2D)bi.getGraphics();
        IDrawer dr = new SimpleEdgeDrawer(sc, graphics);
        scene.drawScene(dr, cam);
        g.drawImage(bi, 0, 0, null);
        graphics.dispose();
    }

    @Override
    public void shouldRepaint() {
        repaint();
    }
}
