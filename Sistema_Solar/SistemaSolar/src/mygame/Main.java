package mygame;

import com.jme3.app.SimpleApplication;
import com.jme3.material.Material;
import com.jme3.math.ColorRGBA;
import com.jme3.math.FastMath;
import com.jme3.renderer.RenderManager;
import com.jme3.scene.Geometry;
import com.jme3.scene.Node;
import com.jme3.scene.shape.Sphere;

/**
 * Recordemos que un scene graph se compone de dos tipos de objetos que heredan 
 * de la clase Spatial, estos son: Node y Geometry, donde los primeros no se pueden visualizar.
 * Los segundos contienen toda la información para que el motor pueda mostrar el objeto en la 
 * pantalla: información del modelo(vértices y aristas), material.
 */
public class Main extends SimpleApplication {

    public static void main(String[] args) {
        Main app = new Main();
        app.start();
    }

    @Override
    public void simpleInitApp() {
        
        
        /**
         * Los objetos Node podrías imaginarlos como cajas que contendrán otras cajas o modelos (esferas, 
         * personaje, etc.). Al igual que si mueves una caja, se mueve todo lo de su interior, si rotas 
         * o trasladas un Node, aplica lo mismo a los objetos que contiene. 
         **/
        Node pivottierra = new Node("nodo_pivotetierra");
        
        //declaracion de los nodos pivote para los planetas que se van a utilizar
        Node pivotjupiter = new Node("nodo_pivotejupiter");
        Node pivoturano = new Node("nodo_pivoteurano");
        
        
        Node nodeSol = new Node("nodo_sol");
        Node nodeTierra = new Node("nodo_tierra");
        
        //creacion del nodo de los planetas
        Node nodeJupiter = new Node("nodo_jupiter"); 
        Node nodeUrano = new Node("nodo_urano");     
        /**
         * Para poder visualizar un objeto en jMonkey se necesita definir un objeto Geometry, 
         * el cual requiere los vértices y aristas del objeto tridimensional, así como el 
         * material de este.
         */
        Sphere sol = new Sphere(30, 30, 1.5f);
        Geometry geomSol = new Geometry("Sol", sol);
        /**
         * Ya que utilizaremos una figura geométrica básica, la “esfera”, el motor proporción 
         * una clase la cual sólo debemos revisar en el API, para identificar cada parámetro de 
         * su constructor. En este caso, para la clase Sphere(int zSample, int radialSamples, 
         * float radius), zSample – El número de muestras a lo largo del eje z, 
         * radialSample -  El número de muestras a lo largo del radial, radius – El radio de la esfera.
         * 
         **/
        Sphere tierra = new Sphere(30, 30, 0.7f);
        Geometry geomTierra = new Geometry("Tierra", tierra);
        Sphere luna = new Sphere(30, 30, 0.3f);
        Geometry geomLuna = new Geometry("Luna", luna);
        
        
        /*Creacion de  los obejtos geometricos  circulares que representaran a los 2 
        planetas que se  agregaran al proyecto en este caso Júpiter y Urano*/
        Sphere jupiter = new Sphere(30,30,0.5f);
        Geometry geomJupiter = new Geometry("Jupiter", jupiter);
       
        Sphere urano = new Sphere(30,30,0.6f);
        Geometry geomUrano = new Geometry("Urano", urano);
        
        /**
         * Por el momento utilizaremos materiales que en si son simples colores.  
         * Mas adelante tendrán otra apariencia.
         **/
        Material matY = new Material(assetManager, "Common/MatDefs/Misc/Unshaded.j3md");
        matY.setColor("Color", ColorRGBA.Yellow);
        geomSol.setMaterial(matY);
        
        Material matB = new Material(assetManager, "Common/MatDefs/Misc/Unshaded.j3md");
        matB.setColor("Color", ColorRGBA.Blue);        
        geomTierra.setMaterial(matB);
        
        
        
        Material matW = new Material(assetManager, "Common/MatDefs/Misc/Unshaded.j3md");
        matW.setColor("Color", ColorRGBA.White);
        geomLuna.setMaterial(matW);
        
        geomLuna.setLocalTranslation(1.7f, 0f, 0f);
        
        
        /*Aquí se les asignan sus propiedades o caracterisitas, como el materia y el color
        a los objetos que representan los planetas*/
        Material matO = new Material(assetManager, "Common/MatDefs/Misc/Unshaded.j3md");
        matO.setColor("Color", ColorRGBA.Orange);        
        geomJupiter.setMaterial(matO);
        
        Material matG = new Material(assetManager, "Common/MatDefs/Misc/Unshaded.j3md");
        matG.setColor("Color", ColorRGBA.Gray);        
        geomUrano.setMaterial(matG);
 
           
        
        /**
         * Los Nodes son un medio para agrupar otros nodos y/o objetos del tipo Geometry. Además, 
         * se utilizan comúnmente para aplicar transformaciones a los spatials que agrupan.
         * 
         * A través del comando “attachChild( Geometry)” estaremos organizando lo que cada 
         * Node tendrá, esto lo hacemos de acuerdo a una idea que definimos previamente, la 
         * cual nos permitirá generar la ilusión de rotación en forma de orbita para cada uno de 
         * nuestros planetas
         */
        nodeTierra.attachChild(geomTierra);
        nodeTierra.attachChild(geomLuna);
        
        //declaracon de nodo con relacion al objeto que afecta o pertenece
        nodeJupiter.attachChild(geomJupiter);
        nodeUrano.attachChild(geomUrano);
        
        nodeSol.attachChild(geomSol);
        
        //la luna gira al rededor de su pivote la tierra sobre el eje Z
        nodeTierra.rotate(0, 0, 30 * FastMath.DEG_TO_RAD);
        
        
        //se define la distancia en la que realizara la translacion con respecto 
        //nodo de pivote de cada objeto o planeta
        nodeTierra.setLocalTranslation(4f, 0, 0f);
        nodeJupiter.setLocalTranslation(7f, 0, 0f);
        nodeUrano.setLocalTranslation(12f, 0, 0f);
        
        
        pivottierra.attachChild(nodeSol);
        pivottierra.attachChild(nodeTierra);
        pivotjupiter.attachChild(nodeJupiter);
        pivoturano.attachChild(nodeUrano);
       
        //el planeta tierra gira sobre el eje de las X 
        pivottierra.rotate(30 * FastMath.DEG_TO_RAD,0,0);
        
        //el planeta jupiter gira sobre el eje de la Z 
        pivotjupiter.rotate(0,0,30 * FastMath.DEG_TO_RAD);
        
        /**
         * Recuerda, para que se pueda visualizar algún Spatial(Node o Geometry) en el escenario, 
         * ecesita estar adjuntado al “rootNode”. Además, ya que se genera un relación de padre – hijo, 
         * si agregas al padre por lo tanto también agregas el hijo, regresando a la visualización de cajas, 
         * si agregas una caja, por transitividad, también lo que este adentro estará agregado. 
        **/
        rootNode.attachChild(pivottierra);
        rootNode.attachChild(pivotjupiter);
        rootNode.attachChild(pivoturano);
    }

    @Override
    public void simpleUpdate(float tpf) {
        //TODO: add update code
        /**
         * Time per frames = tpf
         * tpf Indica el tiempo que toma al motor genera un frame(imagen renderizada en la pantalla) 
         * antes de entrar nuevamente al metodo simpleUpdate para generar un nuevo frame.
         * Esto indica que si utilizamos esta variable para determinar la cantidad de movimiento en 
         * cada frame, este movimiento será más o menos rápido, dependiendo de la máquina que se utilice, 
         * pero por el momento podremos utilizar esta variable para actualizar la rotación de cada objeto 
         * en cada ciclo de la función simpleUpdate
        **/
        
        
        rootNode.getChild("nodo_tierra").rotate(0,tpf, 0);
        
        
        // tpf/3 indica que rotara 1/3 de la velocidad de la variable tpf
        //sobre el eje Y
        rootNode.getChild("nodo_pivotetierra").rotate(0,tpf/3, 0);
        
        // tpf/4 indica que rotara 1/3 de la velocidad de la variable tpf
        //sobre el eje Y
        rootNode.getChild("nodo_pivotejupiter").rotate(0,tpf/4, 0);    
        
        // tpf/5 indica que rotara 1/3 de la velocidad de la variable tpf
        //sobre el eje Y
        rootNode.getChild("nodo_pivoteurano").rotate(0,tpf/5, 0);  
    }

    @Override
    public void simpleRender(RenderManager rm) {
        //TODO: add render code
    }
}
