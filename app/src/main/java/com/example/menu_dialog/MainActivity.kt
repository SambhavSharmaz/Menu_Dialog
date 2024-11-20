package com.example.menu_dialog

import android.os.Bundle
import android.view.*
import android.widget.*
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Setup Toolbar
        val toolbar: Toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)

        val contextMenuButton: Button = findViewById(R.id.contextMenuButton)
        val popupMenuButton: Button = findViewById(R.id.popupMenuButton)
        val alertDialogButton: Button = findViewById(R.id.alertDialogButton)
        val customAlertDialogButton: Button = findViewById(R.id.customAlertDialogButton)

        // Context Menu
        registerForContextMenu(contextMenuButton)

        // Popup Menu
        popupMenuButton.setOnClickListener {
            showPopupMenu(it)
        }

        // Alert Dialog
        alertDialogButton.setOnClickListener {
            showAlert()
        }

        // Custom Alert Dialog
        customAlertDialogButton.setOnClickListener {
            showCustomAlert()
        }
    }

    // Toolbar Menu
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_option, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.home -> showToast("Home selected")
            R.id.settings -> showToast("Settings selected")
            R.id.logout -> showToast("Logout selected")
        }
        return super.onOptionsItemSelected(item)
    }

    // Context Menu
    override fun onCreateContextMenu(menu: ContextMenu?, v: View?, menuInfo: ContextMenu.ContextMenuInfo?) {
        super.onCreateContextMenu(menu, v, menuInfo)
        menu?.setHeaderTitle("Context Menu")
        menuInflater.inflate(R.menu.menu_option, menu)
    }

    override fun onContextItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.home -> showToast("Context: Home selected")
            R.id.settings -> showToast("Context: Settings selected")
            R.id.logout -> showToast("Context: Logout selected")
        }
        return super.onContextItemSelected(item)
    }

    // Popup Menu
    private fun showPopupMenu(view: View) {
        val popupMenu = PopupMenu(this, view)
        popupMenu.menuInflater.inflate(R.menu.menu_option, popupMenu.menu)
        popupMenu.setOnMenuItemClickListener { item ->
            when (item.itemId) {
                R.id.home -> showToast("Popup: Home selected")
                R.id.settings -> showToast("Popup: Settings selected")
                R.id.logout -> showToast("Popup: Logout selected")
            }
            true
        }
        popupMenu.show()
    }

    // Alert Dialog
    private fun showAlert() {
        val alertDialog = AlertDialog.Builder(this)
            .setTitle("Alert Dialog")
            .setMessage("This is an enhanced alert dialog with additional actions.")
            .setPositiveButton("OK") { dialog, _ -> dialog.dismiss() }
            .setNegativeButton("Cancel") { dialog, _ -> dialog.dismiss() }
            .setNeutralButton("Help") { dialog, _ -> showToast("Help clicked") }
            .create()
        alertDialog.show()
    }

    // Custom Alert Dialog
    private fun showCustomAlert() {
        val customView = layoutInflater.inflate(R.layout.custom_dialog, null)
        val nameInput: EditText = customView.findViewById(R.id.nameInput)

        val customAlertDialog = AlertDialog.Builder(this)
            .setView(customView)
            .setTitle("Custom Dialog")
            .setPositiveButton("Submit") { dialog, _ ->
                showToast("Hello, ${nameInput.text}")
                dialog.dismiss()
            }
            .setNegativeButton("Cancel") { dialog, _ -> dialog.dismiss() }
            .create()
        customAlertDialog.show()
    }

    // Utility function to show Toast
    private fun showToast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }
}
